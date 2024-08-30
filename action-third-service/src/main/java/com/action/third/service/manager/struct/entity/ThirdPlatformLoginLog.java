package com.action.third.service.manager.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 第三方平台登录日志信息
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("thrid_platform_login_log")
public class ThirdPlatformLoginLog extends BaseEntity {
    @TableId("id")
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("username")
    private String username;
    @TableField("user_token")
    private String userToken;
    @TableField("service_url")
    private String serviceUrl;
    @TableField("url")
    private String url;
}
