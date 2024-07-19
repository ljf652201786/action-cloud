package com.action.system.event;

import com.action.common.core.listener.ActionEvent;
import com.action.common.core.listener.IEventHander;

/**
 * @Description: 系统服务事件
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/15
 */
public class ActionSystemEvent extends ActionEvent {

    public ActionSystemEvent(Object source) {
        super(source);
    }

    public ActionSystemEvent(Object source, String name, IEventHander iEventHander) {
        super(source, name, iEventHander);
    }
}
