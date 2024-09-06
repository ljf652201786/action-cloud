package com.action.third.service.impl;

import com.action.common.core.constants.StringPool;
import com.action.common.core.handle.RedisCacheHandle;
import com.action.common.core.tool.JsonUtils;
import com.action.common.network.holder.WebClientManager;
import com.action.common.network.properties.NetWorkManagerProperties;
import com.action.common.network.service.IWebClientAuthService;
import com.action.common.network.struct.RemoteWebClient;
import com.action.common.network.utils.WebClientUtils;
import com.action.third.service.manager.service.IThirdPlatformRequestLogService;
import com.action.third.service.util.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.action.common.common.RedisSetConstants.OTHER_WEBCLIENT;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/26
 */
@Service
@RequiredArgsConstructor
public class WebClientAuthServiceImpl implements IWebClientAuthService {
    private static final Logger logger = LoggerFactory.getLogger(WebClientAuthServiceImpl.class);
    private static final List<String> ingoreUrlList = List.of("/um/user/login", "/corerain-sysauth/login", "/corerain-sysauth/getToken", "corerain-app/appsPage", "corerain-app/create", "corerain-app/update", "corerain-app/delete");
    private final IThirdPlatformRequestLogService iThirdPlatformRequestLogService;
    private final RedisCacheHandle redisCacheHandle;
    private final NetWorkManagerProperties netWorkManagerProperties;

    /*
     * 初始请求头设置
     * */
    @Override
    public void header(String baseUrl, HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    /*
     * 动态设置请求
     * */
    @Override
    public void requestFilter(String baseUrl, ClientRequest clientRequest) {
        HttpServletRequest currentRequest = RequestUtils.getCurrentRequest();
        //登录接口不设置token
        if (ingoreUrlList.contains(currentRequest.getRequestURI())) {
            return;
        }
        //通过redis 验证redis中是否存在token，如果不存在就重新获取token
        String webClientToken = null;
        Optional<RemoteWebClient> webClientOptional = netWorkManagerProperties.getRemoteHttp().getRemoteWebClientByServiceUrl(baseUrl);
        if (!webClientOptional.isPresent()) {
            return;
        }
        RemoteWebClient webClient = webClientOptional.get();

        Object o = redisCacheHandle.get(OTHER_WEBCLIENT + baseUrl);
        if (Objects.nonNull(o)) {
            webClientToken = (String) o;
        } else {
            webClientToken = WebClientManager.getWebClientToken(webClient.getTokenApiUrl(), baseUrl, String.class);
            if (StringUtils.isNotBlank(webClientToken)) {
                if (!JsonUtils.isValidJson(webClientToken)) {
                    redisCacheHandle.set(OTHER_WEBCLIENT + baseUrl, webClientToken, webClient.getExpiryTime());
                }
            }
        }
        logger.info("AccessToken has been obtained:{}", webClientToken);

        if (StringUtils.isNotEmpty(webClientToken)) {
            //重新设置请求头
            WebClientUtils.putHeader(clientRequest, webClient.getTokenKey(), webClient.getTokenBearer() + StringPool.SPACE + webClientToken);
        }
        logger.info("Dynamic addition of authentication token completed");

    }

    /*
     * 执行成功回调
     * */
    @Override
    public void successHander(ClientResponse clientResponse) {
        System.out.println("执行成功回调" + clientResponse.request().getURI().getPath());
        iThirdPlatformRequestLogService.savelog(clientResponse);
    }

    /*
     * 执行成功回调
     * */
    @Override
    public void errorHander(ClientResponse clientResponse) {
        System.out.println("执行失败回调" + clientResponse.request().getURI().getPath());
        iThirdPlatformRequestLogService.savelog(clientResponse);
    }
}
