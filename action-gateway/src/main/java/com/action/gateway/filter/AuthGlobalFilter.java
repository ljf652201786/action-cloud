/*
package com.action.gateway.filter;

import com.action.gateway.properties.AuthProperties;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

*/
/*
 *
 * @Description: 鉴权过滤器
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/01
 *//*

@Service
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Resource
    private AuthProperties authProperties;

    //完成判断逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //获取header
        HttpHeaders headers = request.getHeaders();
        // 校验token是否为空
        List<String> tokens = headers.get("token");
        if (CollectionUtils.isEmpty(tokens)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            throw new RuntimeException("没有权限");
        }
        //调用chain.filter继续向下游执行
        return chain.filter(exchange);

    }

    //顺序,数值越小,优先级越高
    @Override
    public int getOrder() {
        return 1;
    }

    */
/*private boolean tokenNotRequired(ServerHttpRequest request) {
        List<String> ignoreUrls = resourceProperties.getIgnoreUrls();
        if (Objects.nonNull(ignoreUrls)) {
            Optional<String> optional = ignoreUrls.stream().filter(url -> new AntPathRequestMatcher(url).matches(request)).findFirst();
            if (optional.isPresent()) {
                return true;
            }
        }
        return false;
    }*//*

}

*/
