package com.action.gateway.event;

import com.action.common.core.listener.ActionEvent;
import com.action.common.core.listener.IEventHander;

/**
 * @Description: 网关事件
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/15
 */
public class ActionGateWayEvent extends ActionEvent {

    public ActionGateWayEvent(Object source) {
        super(source);
    }

    public ActionGateWayEvent(Object source, String name, IEventHander iEventHander) {
        super(source, name, iEventHander);
    }
}
