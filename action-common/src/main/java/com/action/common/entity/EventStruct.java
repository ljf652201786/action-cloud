package com.action.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 事件消息结构体
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/12
 */
@Data
public class EventStruct {
    private String serviceName;
    private String message;
    private Date time;
}
