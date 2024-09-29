package com.action.gateway.config;

import com.action.common.struct.ActionInterfaceEncryptStruct;
import com.alibaba.nacos.common.utils.CollectionUtils;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "security-manage")
@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
public class GateWaySecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(GateWaySecurityConfig.class);

    /**
     * 黑名单请求路径列表
     */
    @Setter
    private List<String> blacklistPaths;


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchange ->
                        {
                            if (CollectionUtils.isNotEmpty(blacklistPaths)) {
                                exchange.pathMatchers(blacklistPaths.stream().collect(Collectors.joining(","))).authenticated();
                            }
                            exchange.anyExchange().permitAll();
                        }
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    public ActionInterfaceEncryptStruct actionInterfaceEncryptStruct() {
        return new ActionInterfaceEncryptStruct();
    }
}