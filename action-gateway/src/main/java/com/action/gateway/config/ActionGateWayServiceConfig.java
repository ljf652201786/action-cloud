package com.action.gateway.config;

import com.action.common.core.entity.EventStruct;
import com.action.common.core.listener.IEventService;
import com.action.gateway.event.ActionGateWayEvent;
import com.action.gateway.event.ActionGateWayEventHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 网关服务配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Configuration
@RequiredArgsConstructor
public class ActionGateWayServiceConfig implements ApplicationRunner {
    private final IEventService iEventService;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        iEventService.registerEvent(ActionGateWayEvent.class, new ActionGateWayEvent(new EventStruct(), appName, new ActionGateWayEventHandler()));
    }

}
