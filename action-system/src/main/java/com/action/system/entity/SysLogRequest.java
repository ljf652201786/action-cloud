package com.action.system.entity;

import com.action.call.vo.LogRequestVo;
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

    public SysLogRequest buildSysLogRequest(LogRequestVo logRequestVo) {
        this.model_name = logRequestVo.getModel_name();
        this.business_type = logRequestVo.getBusiness_type();
        this.operator_type = logRequestVo.getOperator_type();
        this.operator_user = logRequestVo.getOperator_user();
        this.method_name = logRequestVo.getMethod_name();
        this.request_method = logRequestVo.getRequest_method();
        this.request_url = logRequestVo.getRequest_url();
        this.request_ip = logRequestVo.getRequest_ip();
        this.request_location = logRequestVo.getRequest_location();
        this.request_param = logRequestVo.getRequest_param();
        this.request_time = logRequestVo.getRequest_time();
        this.response_param = logRequestVo.getResponse_param();
        this.login_status = logRequestVo.getLogin_status();
        this.exception_msg = logRequestVo.getException_msg();
        return this;
    }
}
