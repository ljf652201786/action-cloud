package com.action.gateway.config;

import com.action.common.core.listener.IEventService;
import com.action.common.entity.EventStruct;
import com.action.gateway.event.ActionGateWayEvent;
import com.action.gateway.event.ActionGateWayEventHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 网关服务配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Configuration
@RequiredArgsConstructor
public class ActionGateWayServiceConfig {
    private final IEventService iEventService;

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    public void configService() {
        iEventService.registerEvent(ActionGateWayEvent.class, new ActionGateWayEvent(new EventStruct(), appName, new ActionGateWayEventHandler()));
    }

}
