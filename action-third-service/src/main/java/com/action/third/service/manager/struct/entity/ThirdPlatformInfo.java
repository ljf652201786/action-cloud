package com.action.third.service.manager.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 第三方平台信息
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("thrid_platform_info")
public class ThirdPlatformInfo extends BaseEntity {
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
    @TableField("service_url")
    private String serviceUrl;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("user_token")
    private String userToken;
    @TableField("app_id")
    private String appId;
    @TableField("app_secret")
    private String appSecret;
    @TableField("status")
    private String status;

}
