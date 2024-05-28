package com.action.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_log_sms")
public class SysLogSms {
    @TableId(value = "id")
    private String id;
    @TableField("username")
    private String username;
    @TableField("phone")
    private String phone;
    @TableField("status")
    private String status;
    @TableField("msg")
    private String msg;
    @TableField("request_time")
    private Date requestTime;
    @TableField("expire_time")
    private String expireTime;
}
