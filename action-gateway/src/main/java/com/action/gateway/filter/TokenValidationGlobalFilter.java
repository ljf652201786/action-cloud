package com.action.gateway.filter;

import com.action.common.common.RedisSetConstants;
import com.action.common.core.common.ResultCode;
import com.action.common.core.constants.ActionConstants;
import com.action.common.core.constants.JwtClaimConstants;
import com.action.common.core.service.RedisCacheServices;
import com.action.gateway.util.WebFluxUtils;
import com.nimbusds.jose.JWSObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * @Description: Token 验证全局过滤器
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/28
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TokenValidationGlobalFilter implements GlobalFilter, Ordered {

    private final RedisCacheServices redisCacheServices;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("x-forwarded-for", new String[]{exchange.getRequest().getRemoteAddress().getHostString()})
                .build();
        ServerHttpResponse response = exchange.getResponse();

        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNoneBlank(authorization) && StringUtils.startsWith(authorization, ActionConstants.BEARER_PREFIX)) {
            try {
                String token = authorization.substring(ActionConstants.BEARER_PREFIX.length());
                JWSObject jwsObject = JWSObject.parse(token);
                String jti = (String) jwsObject.getPayload().toJSONObject().get(JwtClaimConstants.JTI);
                Boolean isBlackToken = redisCacheServices.exists(RedisSetConstants.TOKEN_BLACKLIST_KEY + jti);
                if (Boolean.TRUE.equals(isBlackToken)) {
                    return WebFluxUtils.writeErrorResponse(response, ResultCode.TOKEN_ACCESS_FORBIDDEN);
                }
            } catch (ParseException e) {
                log.error("Parsing token failed in TokenValidationGlobalFilter", e);
                return WebFluxUtils.writeErrorResponse(response, ResultCode.TOKEN_INVALID);
            }
        }
        return chain.filter(exchange.mutate().request(request).build())/*.then(Mono.fromRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println("后置过滤器，待实现");
            }
        }))*/;
    }

    @Override
    public int getOrder() {
        return -100;
    }


}
