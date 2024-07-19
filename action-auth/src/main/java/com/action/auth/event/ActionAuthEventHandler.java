package com.action.auth.event;

import com.action.common.core.listener.IEventHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: action认证服务事件处理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Component
public class ActionAuthEventHandler implements IEventHander {
    private static final Logger log = LoggerFactory.getLogger(ActionAuthEventHandler.class);

    @Override
    public void invoke(Object o) {
        log.info("Received event with message:{}", o.toString());
    }
}