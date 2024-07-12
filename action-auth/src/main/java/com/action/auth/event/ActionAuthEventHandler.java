package com.action.auth.event;

import com.action.common.core.listener.IEventHander;
import org.springframework.stereotype.Component;

/**
 * @Description: action认证服务事件处理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Component
public class ActionAuthEventHandler implements IEventHander {

    @Override
    public Object invoke(Object o) {
        System.out.println("执行方法:" + o.toString());
        return null;
    }
}