package com.action.business.config;

import com.action.business.listener.event.ActionBusinessEvent;
import com.action.business.listener.event.ActionBusinessEventHandler;
import com.action.common.core.listener.IEventService;
import com.action.common.entity.EventStruct;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 业务服务配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Configuration
@RequiredArgsConstructor
public class ActionBusinessServiceConfig {
    private final IEventService iEventService;

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    public void configService() {
        iEventService.registerEvent(ActionBusinessEvent.class, new ActionBusinessEvent(new EventStruct(), appName, new ActionBusinessEventHandler()));
    }

}
