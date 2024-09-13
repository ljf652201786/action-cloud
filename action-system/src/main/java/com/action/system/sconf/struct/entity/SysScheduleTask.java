package com.action.system.sconf.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 定时任务表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_scheduled_task")
public class SysScheduleTask extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("sign")
    private String sign;
    @TableField("task_name")
    private String taskName;
    @TableField("task_cron")
    private String taskCron;
    @TableField("bean_name")
    private String beanName;
    @TableField("method_name")
    private String methodName;
    @TableField("params")
    private String params;
    @TableField("status")
    private String status;
}
