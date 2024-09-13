package com.action.system.manager.struct.entity;

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
@TableName("sys_log_request")
public class SysLogRequest {
    @TableId(value = "id")
    private String id;
    @TableField("model_name")
    private String model_name;
    @TableField("business_type")
    private String business_type;
    @TableField("operator_type")
    private String operator_type;
    @TableField("operator_user")
    private String operator_user;
    @TableField("method_name")
    private String method_name;
    @TableField("request_method")
    private String request_method;
    @TableField("request_url")
    private String request_url;
    @TableField("request_ip")
    private String request_ip;
    @TableField("request_location")
    private String request_location;
    @TableField("request_param")
    private String request_param;
    @TableField("request_time")
    private Date request_time;
    @TableField("response_param")
    private String response_param;
    @TableField("login_status")
    private String login_status;
    @TableField("exception_msg")
    private String exception_msg;
}
