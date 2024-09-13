package com.action.system.manager.struct.dto;

import com.action.system.bsup.struct.entity.SysScope;
import com.action.system.manager.struct.entity.SysUser;

import java.util.List;

/**
 * @Description: 用户注册类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/03
 */
public class SysUserDto extends SysUser {
    private String code;
    private String tenantId;
    private List<String> groupList;
    private List<String> roleList;
    private List<SysScope> sysScopeList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public List<String> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<SysScope> getSysScopeList() {
        return sysScopeList;
    }

    public void setSysScopeList(List<SysScope> sysScopeList) {
        this.sysScopeList = sysScopeList;
    }
}
