package com.action.system.config;

import com.action.common.core.listener.IEventService;
import com.action.common.entity.EventStruct;
import com.action.system.event.ActionSystemEvent;
import com.action.system.event.ActionSystemEventHandler;
import com.action.system.sconf.service.ISysScheduleTaskService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 系统服务配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Configuration
@RequiredArgsConstructor
public class ActionSystemServiceConfig {
    private final IEventService iEventService;
    private final ISysScheduleTaskService iSysScheduleTaskService;

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    public void configService() {
        iEventService.registerEvent(ActionSystemEvent.class, new ActionSystemEvent(new EventStruct(), appName, new ActionSystemEventHandler()));
        iSysScheduleTaskService.initScheduleTask();
    }

}
