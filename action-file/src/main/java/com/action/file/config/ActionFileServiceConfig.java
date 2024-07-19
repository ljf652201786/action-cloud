package com.action.file.config;

import com.action.common.core.listener.IEventService;
import com.action.common.entity.EventStruct;
import com.action.file.event.ActionFileEvent;
import com.action.file.event.ActionFileEventHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 文件服务配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Configuration
@RequiredArgsConstructor
public class ActionFileServiceConfig {
    private final IEventService iEventService;

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    public void configService() {
        iEventService.registerEvent(ActionFileEvent.class, new ActionFileEvent(new EventStruct(), appName, new ActionFileEventHandler()));
    }

}
