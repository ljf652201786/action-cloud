package com.action.business.listener.event;

import com.action.common.core.listener.IEventHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiFunction;

/**
 * @Description: 业务事件处理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/15
 */
public class ActionBusinessEventHandler implements IEventHander {
    private static final Logger log = LoggerFactory.getLogger(ActionBusinessEventHandler.class);

    @Override
    public void invoke(Object o, BiFunction<Object, Object, Object> function) {
        log.info("Received event with message:{}", o.toString());
    }

    @Override
    public BiFunction<Object, Object, Object> getFun() {
        return (o1, o2) -> o1;
    }
}
