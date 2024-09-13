package com.action.gateway.filter;

import com.action.common.core.constants.StringPool;
import com.action.common.encrypt.symmetric.AESEncrypt;
import com.action.common.entity.ActionInterfaceEncryptStruct;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

import static com.action.common.core.constants.ActionConstants.BODY_DATA_KEY;
import static com.action.common.core.constants.ActionConstants.PARAM_DATA_KEY;
import static com.action.gateway.filter.GlobalCacheRequestBodyFilter.checkIgnore;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

/**
 * 参数解密
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GlobalRequestDecryptFilter implements GlobalFilter, Ordered {
    private final ActionInterfaceEncryptStruct actionInterfaceEncryptStruct;
    private static Joiner joiner = Joiner.on("");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (checkIgnore(request)) {
            return chain.filter(exchange);
        }

        HttpHeaders headers = request.getHeaders();
        MediaType contentType = headers.getContentType();

        // 创建新的request
        ServerHttpRequest newRequest = buildNewRequest(request);
        // 构建新的请求头
        HttpHeaders newheaders = buildNewHeaders(headers);

        //获取返回
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();

        if ((request.getMethod() == HttpMethod.GET || request.getMethod() == HttpMethod.DELETE || request.getMethod() == HttpMethod.PUT)
                && Objects.isNull(contentType)) {
            newRequest = this.paramRequestHandle(exchange, newRequest);
        } else if ((request.getMethod() == HttpMethod.POST || request.getMethod() == HttpMethod.PUT) &&
                (Objects.nonNull(contentType) &&
                        (MediaType.APPLICATION_JSON.equals(contentType) || MediaType.APPLICATION_JSON_UTF8.equals(contentType))
                )
        ) {
            newRequest = this.bodyRequestHandle(exchange, bufferFactory, newRequest, newheaders);
        } else {
            return chain.filter(exchange);
        }

        //将请求头放入request中
        newRequest = setHeadersToRequest(newRequest, newheaders);

        ServerHttpResponseDecorator response = getServerHttpResponseDecorator(exchange, originalResponse, bufferFactory);

        return chain.filter(
                exchange.mutate()
                        .request(newRequest)
                        .response(response)
                        .build()
        );
    }

    /**
     * @Description: 将请求Uri放入request中
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private ServerHttpRequest setUriToRequest(ServerHttpRequest request, URI uri) {
        request = new ServerHttpRequestDecorator(request) {
            @Override
            public URI getURI() {
                return uri;
            }
        };
        return request;
    }

    /**
     * @Description: 将请求参数放入request中
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private ServerHttpRequest setQueryParamsToRequest(ServerHttpRequest request, MultiValueMap<String, String> queryParamMap) {
        request = new ServerHttpRequestDecorator(request) {
            @Override
            public MultiValueMap<String, String> getQueryParams() {
                return queryParamMap;
            }
        };
        return request;
    }

    /**
     * @Description: 将请求头放入request中
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private ServerHttpRequest setHeadersToRequest(ServerHttpRequest request, HttpHeaders headers) {
        request = new ServerHttpRequestDecorator(request) {
            @Override
            public HttpHeaders getHeaders() {
                return headers;
            }
        };
        return request;
    }

    /**
     * @Description: 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private void updateContentLength(HttpHeaders headers, byte[] decrypBytes) {
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        headers.setContentLength(decrypBytes.length);
    }

    /**
     * @Description: 将body参数放入request中
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private ServerHttpRequest setBodyToRequest(ServerHttpRequest request, DataBuffer dataBuffer) {
        request = new ServerHttpRequestDecorator(request) {
            @Override
            public Flux<DataBuffer> getBody() {
                return Flux.just(dataBuffer);
            }
        };
        return request;
    }

    /**
     * @Description: 获取解密后的body数据
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private byte[] getBodyDataByAesDecrypt(byte[] cachedRequestBodyObject) {
        try {
            final byte[] cacheBody = cachedRequestBodyObject;
            byte[] decrypBytes = cacheBody;
            JSONObject data = JSONObject.parseObject(new String(decrypBytes));
            if (Objects.nonNull(data) && StringUtils.isNotBlank(data.getString(BODY_DATA_KEY))) {
                String decryptStr = aesDecrypt(data.getString(BODY_DATA_KEY));
                return decryptStr.getBytes();
            }
            return null;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @Description: AES解密数据
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private String aesDecrypt(String data) {
        return AESEncrypt.of().decrypt(data, actionInterfaceEncryptStruct.getAesKey());
    }

    /**
     * @Description: 构建新的请求
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/12
     */
    private ServerHttpRequest buildNewRequest(ServerHttpRequest request) {
        return request.mutate().uri(request.getURI()).build();
    }

    /**
     * @Description: 构建新的请求头
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/12
     */
    private HttpHeaders buildNewHeaders(HttpHeaders headers) {
        HttpHeaders newheaders = new HttpHeaders();
        newheaders.putAll(headers);
        return newheaders;
    }

    /**
     * @Description: 构建新的Uri
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private URI buildUri(ServerHttpRequest request, MultiValueMap<String, String> params) throws URISyntaxException {
        StringBuilder queryBuilder = new StringBuilder();
        for (String key : params.keySet()) {
            queryBuilder.append(key);
            queryBuilder.append(StringPool.EQUALS);
            queryBuilder.append(params.getFirst(key));
            queryBuilder.append(StringPool.AMPERSAND);
        }
        queryBuilder.deleteCharAt(queryBuilder.length() - 1);

        URI uri = request.getURI();
        return new URI(uri.getScheme(),
                uri.getUserInfo(),
                uri.getHost(),
                uri.getPort(),
                uri.getPath(),
                queryBuilder.toString(),
                uri.getFragment());
    }

    /**
     * @Description: 构建请求参数
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private MultiValueMap<String, String> buildMultiValueMap(String dataJson) {
        JSONObject jsonObject = JSON.parseObject(dataJson);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>(jsonObject.size());
        for (String key : jsonObject.keySet()) {
            map.put(key, Lists.newArrayList(jsonObject.getString(key)));
        }
        return map;
    }

    /**
     * @Description: 处理post请求时的数据处理
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private ServerHttpRequest bodyRequestHandle(ServerWebExchange exchange, DataBufferFactory bufferFactory, ServerHttpRequest newRequest, HttpHeaders headers) {
        String url = exchange.getRequest().getURI().toString();

        newRequest = paramRequestHandle(exchange, newRequest);

        Object cachedRequestBodyObject = exchange.getAttributeOrDefault(url, null);
        if (cachedRequestBodyObject != null) {
            //获取解密后的body数据
            byte[] decrypBytes = getBodyDataByAesDecrypt((byte[]) cachedRequestBodyObject);

            // 根据解密后的参数重新构建请求
            DataBuffer dataBuffer = bufferFactory.wrap(decrypBytes);
            newRequest = setBodyToRequest(newRequest, dataBuffer);

            // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
            updateContentLength(headers, decrypBytes);

            // 把解密后的数据重置到exchange自定义属性中
            exchange.getAttributes().put(url, decrypBytes);
        }
        return newRequest;
    }

    /**
     * @Description: 处理参数请求的数据处理
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private ServerHttpRequest paramRequestHandle(ServerWebExchange exchange, ServerHttpRequest newRequest) {
        try {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getQueryParams().isEmpty()) {
                String paramData = request.getQueryParams().getFirst(PARAM_DATA_KEY);
                if (StringUtils.isEmpty(paramData)) {
                    throw new NullPointerException("The parameter cannot be empty");
                }
                String decryptStr = aesDecrypt(paramData);
                MultiValueMap<String, String> paramsMap = buildMultiValueMap(decryptStr);
                URI uri = buildUri(request, paramsMap);
                newRequest = setQueryParamsToRequest(newRequest, paramsMap);
                newRequest = setUriToRequest(newRequest, uri);
                return newRequest;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return newRequest;
    }

    /**
     * @Description: 处理响应数据
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/11
     */
    private ServerHttpResponseDecorator getServerHttpResponseDecorator(ServerWebExchange exchange, ServerHttpResponse originalResponse, DataBufferFactory bufferFactory) {
        ServerHttpResponseDecorator response = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (getStatusCode().equals(HttpStatus.OK) && body instanceof Flux) {
                    // 获取ContentType，判断是否返回JSON格式数据
                    String originalResponseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                    if (StringUtils.isNotBlank(originalResponseContentType) && (originalResponseContentType.contains(MediaType.APPLICATION_JSON_VALUE) || originalResponseContentType.contains(MediaType.APPLICATION_JSON_UTF8_VALUE))) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        //（返回数据内如果字符串过大，默认会切割）解决返回体分段传输
                        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                            List<String> list = Lists.newArrayList();
                            dataBuffers.forEach(dataBuffer -> {
                                try {
                                    byte[] content = new byte[dataBuffer.readableByteCount()];
                                    dataBuffer.read(content);
                                    DataBufferUtils.release(dataBuffer);
                                    list.add(new String(content, Charset.forName(StringPool.UTF_8)));
                                } catch (Exception e) {
                                    log.info("Loading Response byte stream exception, reason for failure：{}", Throwables.getStackTraceAsString(e));
                                }
                            });
                            String responseData = joiner.join(list);
                            try {
                                responseData = AESEncrypt.of().encrypt(responseData, actionInterfaceEncryptStruct.getAesKey());
                            } catch (Exception e) {
                                e.printStackTrace();
                                log.error("Return parameter result encryption exception：{}", Throwables.getStackTraceAsString(e));
                            }

                            byte[] uppedContent = new String(responseData.getBytes(), Charset.forName(StringPool.UTF_8)).getBytes();
                            originalResponse.getHeaders().setContentLength(uppedContent.length);
                            return bufferFactory.wrap(uppedContent);
                        }));
                    }
                }
                return super.writeWith(body);
            }

            @Override
            public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
                return writeWith(Flux.from(body).flatMapSequential(p -> p));
            }
        };
        return response;
    }


    @Override
    public int getOrder() {
        return -80;
    }
}
