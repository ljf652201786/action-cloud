package com.action.system.manager.struct.entity;

import com.action.common.mybatisplus.extend.annotation.Condition;
import com.baomidou.mybatisplus.annotation.IdType;
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
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @TableField("tenant_id")
    private String tenantId;
    @TableField("operator_user")
    private String operatorUser;
    @TableField("method_name")
    private String methodName;
    @TableField("request_method")
    private String requestMethod;
    @TableField("request_url")
    private String requestUrl;
    @TableField("request_ip")
    private String requestIp;
    @TableField("request_param")
    private String requestParam;
    @TableField("operator_time")
    private Integer operatorTime;
    @Condition(order = Condition.OrderTypeEnums.DESC)
    @TableField("request_time")
    private Date requestTime;
    @TableField("response_param")
    private String responseParam;
    @TableField("login_status")
    private String loginStatus;
}
