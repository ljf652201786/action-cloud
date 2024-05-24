package com.action.auth.config;

import com.action.auth.entity.SecurityUser;
import com.action.auth.common.JwtClaimConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description: JWT 自定义字段配置
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/20
 */
@Configuration
public class JwtTokenCustomizerConfig {

    /**
     * JWT 自定义字段
     *
     * @see <a href="https://docs.spring.io/spring-authorization-server/reference/guides/how-to-custom-claims-authorities.html">Add custom claims to JWT access tokens</a>
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return context -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) && context.getPrincipal() instanceof UsernamePasswordAuthenticationToken) {
                // Customize headers/claims for access_token
                Optional.ofNullable(context.getPrincipal().getPrincipal()).ifPresent(principal -> {
                    JwtClaimsSet.Builder claims = context.getClaims();
                    if (principal instanceof SecurityUser userDetails) { // 系统用户添加自定义字段

                        claims.claim(JwtClaimConstants.USER_ID, userDetails.getId());
                        claims.claim(JwtClaimConstants.USERNAME, userDetails.getUsername());
                        claims.claim(JwtClaimConstants.STATUS, userDetails.getStatus());

                        // 这里存入角色至JWT，解析JWT的角色用于鉴权的位置: ResourceServerConfig#jwtAuthenticationConverter
                        var authorities = AuthorityUtils.authorityListToSet(context.getPrincipal().getAuthorities())
                                .stream()
                                .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
                        claims.claim(JwtClaimConstants.AUTHORITIES, authorities);

                    }
                    /*else if (principal instanceof ThirdDetails thirdDetails) { // 第三方认证添加自定义字段
                        claims.claim(JwtClaimConstants.THIRD_ID, String.valueOf(thirdDetails.getId()));
                    }*/
                });
            }
        };
    }

}
