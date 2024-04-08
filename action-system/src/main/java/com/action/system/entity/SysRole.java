package com.action.system.entity;

import com.action.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description: 角色表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@TableName("sys_role")
public class SysRole extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("code")
    private String code;
    @TableField("role_name")
    private String roleName;
    @TableField("default_role")
    private Boolean defaultRole;
    @TableField("sort")
    private Integer sort;
    @TableField("status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(Boolean defaultRole) {
        this.defaultRole = defaultRole;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
