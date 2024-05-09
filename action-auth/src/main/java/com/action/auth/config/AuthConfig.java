package com.action.auth.config;

import com.action.auth.holder.CaptchaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 认证配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/26
 */
@Configuration
@EnableConfigurationProperties(CaptchaProperties.class)
public class AuthConfig {
}
