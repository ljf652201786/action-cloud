package com.action.file.event;

import com.action.common.core.listener.ActionEvent;
import com.action.common.core.listener.IEventHander;

/**
 * @Description: 文件服务事件
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/15
 */
public class ActionFileEvent extends ActionEvent {

    public ActionFileEvent(Object source) {
        super(source);
    }

    public ActionFileEvent(Object source, String name, IEventHander iEventHander) {
        super(source, name, iEventHander);
    }
}
