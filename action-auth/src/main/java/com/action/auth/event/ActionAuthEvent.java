package com.action.auth.event;

import com.action.common.core.listener.ActionEvent;
import com.action.common.core.listener.IEventHander;

/**
 * @Description: action认证服务事件
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
public class ActionAuthEvent extends ActionEvent {

    public ActionAuthEvent(Object source) {
        super(source);
    }

    public ActionAuthEvent(Object source, String name, IEventHander iEventHander) {
        super(source, name, iEventHander);
    }
}
