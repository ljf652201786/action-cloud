package com.action.auth.config;

import com.action.auth.event.ActionAuthEvent;
import com.action.auth.event.ActionAuthEventHandler;
import com.action.common.core.listener.IEventService;
import com.action.common.entity.EventStruct;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: action认证服务配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Configuration
@RequiredArgsConstructor
public class ActionAuthServiceConfig {
    private final IEventService iEventService;

    @PostConstruct
    public void configService() {
        iEventService.registerEvent(ActionAuthEvent.class, new ActionAuthEvent(new EventStruct(), "action-auth", new ActionAuthEventHandler()));
    }

}
