package com.action.gateway.filter;

import com.action.common.core.constants.ActionConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: 此过滤器只缓存请求的body的数据
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/09
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class GlobalCacheRequestBodyFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (!checkIgnore(request)) {
            return chain.filter(exchange);
        }

        if (HttpMethod.POST == request.getMethod() || HttpMethod.PUT == request.getMethod()) {
            String url = request.getURI().toString();
            Object cachedRequestBodyObject = exchange.getAttributeOrDefault(url, null);
            if (cachedRequestBodyObject != null) {
                return chain.filter(exchange);
            }
            // 如果没有缓存过，获取字节数组存入 exchange 的自定义属性中
            return DataBufferUtils.join(request.getBody())
                    .map(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        DataBufferUtils.release(dataBuffer);
                        return bytes;
                    }).defaultIfEmpty(new byte[0])
                    .doOnNext(bytes -> exchange.getAttributes().put(url, bytes))
                    .then(chain.filter(exchange));
        }
        return chain.filter(exchange);
    }

    /**
     * @Description: 检测是否忽略加解密操作
     * true 不进行操作 false 进行加解密操作
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/12
     */
    public static boolean checkIgnore(ServerHttpRequest request) {
        String isIgnore = request.getHeaders().getFirst(ActionConstants.IGNORE);
        if (StringUtils.isNotEmpty(isIgnore) && isIgnore.equalsIgnoreCase(ActionConstants.IGNORE_VALUE)) {
            return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return -90;
    }
}
