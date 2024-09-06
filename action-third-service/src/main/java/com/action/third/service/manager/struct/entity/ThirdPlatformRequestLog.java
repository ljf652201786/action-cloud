package com.action.third.service.manager.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 第三方平台请求日志信息
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("thrid_platform_request_log")
public class ThirdPlatformRequestLog extends BaseEntity {
    @TableId("id")
    private String id;
    @TableField("authority")
    private String authority;
    @TableField("path")
    private String path;
    @TableField("method_name")
    private String methodName;
    @TableField("url")
    private String url;
    @TableField("status_code")
    private int statusCode;
    @TableField("user_id")
    private String userId;
}
