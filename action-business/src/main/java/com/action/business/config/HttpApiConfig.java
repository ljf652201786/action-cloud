package com.action.business.config;

import com.action.business.api.TestApi;
import com.action.common.network.service.IWebClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: httpApi配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/26
 */
@Configuration
public class HttpApiConfig {
    @Bean
    public TestApi testApi(IWebClientService iWebClientService) {
        return iWebClientService.register("http://127.0.0.1:8574", TestApi.class);
    }
}
