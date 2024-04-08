package com.action.auth.config;/*
package com.springaction.queue.controller;

import com.action.common.auth.config.SecurityConfig;
import com.action.common.auth.provider.WeChatAuthenticationProvider;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

*/
/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/03/27
 *//*

@Configuration
public class ExtendAuthConfig {

    @Autowired
    private SecurityConfig securityConfig;
    @Resource
    private UserDetailsService userDetailsService;

    //扩展第三方登录（微博、淘宝等等）
    @Bean
    public WeChatAuthenticationProvider weChatAuthenticationProvider() {
        WeChatAuthenticationProvider weChatAuthenticationProvider = new WeChatAuthenticationProvider();
        weChatAuthenticationProvider.setUserDetailsService(userDetailsService);
        securityConfig.addProvider(weChatAuthenticationProvider);
        return weChatAuthenticationProvider;
    }
}
*/
