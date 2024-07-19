package com.action.gateway.event;

import com.action.common.core.listener.IEventHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 网关事件处理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/15
 */
@Component
public class ActionGateWayEventHandler implements IEventHander {
    private static final Logger log = LoggerFactory.getLogger(ActionGateWayEventHandler.class);

    @Override
    public void invoke(Object o) {
        log.info("Received event with message:{}", o.toString());
    }
}
