package com.action.system.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 应用表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_app")
public class SysApp extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("app_name")
    private String appName;
    @TableField("app_id")
    private String appId;
    @TableField("app_secret")
    private String appSecret;
    @TableField("user_id")
    private String userId;
    @TableField("access_token")
    private String accessToken;
    @TableField("status")
    private String status;
}
