package com.action.business.listener.event;

import com.action.common.core.listener.ActionEvent;
import com.action.common.core.listener.IEventHander;

/**
 * @Description: 业务事件
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/15
 */
public class ActionBusinessEvent extends ActionEvent {

    public ActionBusinessEvent(Object source) {
        super(source);
    }

    public ActionBusinessEvent(Object source, String name, IEventHander iEventHander) {
        super(source, name, iEventHander);
    }
}
