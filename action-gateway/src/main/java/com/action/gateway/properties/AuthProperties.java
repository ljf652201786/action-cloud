package com.action.gateway.properties;

import com.action.common.core.base.BaseAuthProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 认证配置参数类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/09
 */
@Configuration
@ConfigurationProperties(prefix = "security-auth")
public class AuthProperties extends BaseAuthProperties {

}
