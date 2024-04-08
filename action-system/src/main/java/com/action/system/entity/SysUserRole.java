package com.action.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description: 用户角色表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@TableName("sys_user_role")
public class SysUserRole {
    @TableId(value = "id")
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("role_id")
    private String roleId;
    @TableField("status")
    private String status;

    public SysUserRole() {
    }

    public SysUserRole(String userId, String roleId, String status) {
        this.userId = userId;
        this.roleId = roleId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
