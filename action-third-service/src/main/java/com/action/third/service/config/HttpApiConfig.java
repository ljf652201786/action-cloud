package com.action.third.service.config;

import com.action.common.corerain.api.base.BaseApi;
import com.action.common.corerain.api.service._52.algorithm.AlgorithmApi_52;
import com.action.common.corerain.api.service._52.app.AppApi_52;
import com.action.common.corerain.api.service._52.auth.SysAuthApi_52;
import com.action.common.corerain.api.service._52.callback.CallBackApi_52;
import com.action.common.corerain.api.service._52.usre.UserAPi_52;
import com.action.common.corerain.api.service._52.video.stream.VideoStreamApi_52;
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
    public SysAuthApi_52 sysAuthApi_52(IWebClientService iWebClientService) {
        return iWebClientService.register(0, SysAuthApi_52.class);
    }

    @Bean
    public AppApi_52 appApi_52(IWebClientService iWebClientService) {
        return iWebClientService.register(0, AppApi_52.class);
    }

    @Bean
    public CallBackApi_52 callBackApi_52(IWebClientService iWebClientService) {
        return iWebClientService.register(0, CallBackApi_52.class);
    }

    @Bean
    public UserAPi_52 userAPi_52(IWebClientService iWebClientService) {
        return iWebClientService.register(0, UserAPi_52.class);
    }

    @Bean
    public VideoStreamApi_52 videoStreamApi_52(IWebClientService iWebClientService) {
        return iWebClientService.register(0, VideoStreamApi_52.class);
    }

    @Bean
    public AlgorithmApi_52 algorithmApi_52(IWebClientService iWebClientService) {
        return iWebClientService.register(0, AlgorithmApi_52.class);
    }

}
