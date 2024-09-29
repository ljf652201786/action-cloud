package com.action.common.struct.bo;

import com.action.common.core.entity.EventStruct;
import com.action.common.core.tool.DateUtils;

import java.util.Date;

/**
 * @Description: 日志事件Bo
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/24
 */
public class LogEventBo extends EventStruct {
    public LogEventBo() {
    }

    public LogEventBo(Object data) {
        super("", data, DateUtils.getNowData());
    }

    public LogEventBo(String eventType, Object data, Date time) {
        super(eventType, data, time);
    }
}
