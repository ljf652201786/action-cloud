package com.action.auth.support.oidc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;

/**
 * @Description: 自定义 OIDC 认证转换器
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/20
 */
public class CustomOidcAuthenticationConverter implements AuthenticationConverter {

    private final CustomOidcUserInfoService customOidcUserInfoService;

    public CustomOidcAuthenticationConverter(CustomOidcUserInfoService customOidcUserInfoService) {
        this.customOidcUserInfoService = customOidcUserInfoService;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomOidcUserInfo customOidcUserInfo = customOidcUserInfoService.loadUserByUsername(authentication.getName());
        return new OidcUserInfoAuthenticationToken(authentication, customOidcUserInfo);
    }
}