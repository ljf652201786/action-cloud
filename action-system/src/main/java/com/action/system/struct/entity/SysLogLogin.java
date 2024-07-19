package com.action.system.struct.entity;

import com.action.call.vo.LogLoginVo;
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
@TableName("sys_log_login")
public class SysLogLogin {
    @TableId(value = "id")
    private String id;
    @TableField("username")
    private String username;
    @TableField("ip_address")
    private String ipAddress;
    @TableField("status")
    private String status;
    @TableField("msg")
    private String msg;
    @TableField("request_time")
    private Date requestTime;
    @TableField("browser_name")
    private String browserName;
    @TableField("browser_type")
    private String browserType;
    @TableField("browser_group")
    private String browserGroup;
    @TableField("browser_manufacturer")
    private String browserManufacturer;
    @TableField("browser_renderingengine")
    private String browserRenderingengine;
    @TableField("browser_version")
    private String browserVersion;
    @TableField("os_name")
    private String osName;
    @TableField("os_type")
    private String osType;
    @TableField("os_group")
    private String osGroup;
    @TableField("os_manufacturer")
    private String osManufacturer;
}
