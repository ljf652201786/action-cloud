package com.action.business.config;

import com.action.business.listener.event.ActionBusinessEvent;
import com.action.business.listener.event.ActionBusinessEventHandler;
import com.action.business.network.ActionWebSockerNet;
import com.action.business.network.ActionWebSocketServerInitializer;
import com.action.common.core.entity.EventStruct;
import com.action.common.core.listener.IEventService;
import com.action.common.network.properties.NetWorkManagerProperties;
import com.action.common.network.service.ActionNet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 业务服务配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Configuration
@RequiredArgsConstructor
public class ActionBusinessServiceConfig implements ApplicationRunner {
    private final IEventService iEventService;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        iEventService.registerEvent(ActionBusinessEvent.class, new ActionBusinessEvent(new EventStruct(), appName, new ActionBusinessEventHandler()));
    }

    /*@Bean
    public ActionNet actionHttpNet(NetWorkManagerProperties netWorkManagerProperties) {
        NetWorkManagerProperties.HttpNet httpNetProperties = netWorkManagerProperties.getHttpNet();
        //开启debug模式
        httpNetProperties.setNetDubug(true);
        ActionHttpNet actionHttpNet = new ActionHttpNet(httpNetProperties);
        return actionHttpNet.run();
    }*/

    @Bean
    public ActionNet actionWebSockerNet(NetWorkManagerProperties netWorkManagerProperties) {
        NetWorkManagerProperties.WebSocketNet webSocketNetProperties = netWorkManagerProperties.getWebSocketNet();
        //开启debug模式
//        webSocketNet.setNetDubug(true);
        ActionWebSockerNet actionWebSockerNet = new ActionWebSockerNet(webSocketNetProperties);
        actionWebSockerNet.setChannelInitializer(new ActionWebSocketServerInitializer(webSocketNetProperties));
        return actionWebSockerNet.run();
    }

}
