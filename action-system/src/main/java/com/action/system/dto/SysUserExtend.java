package com.action.system.dto;

import com.action.system.entity.SysScope;
import com.action.system.entity.SysUser;

import java.util.List;

/**
 * @Description: 用户注册类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/03
 */
public class SysUserExtend extends SysUser {
    private String code;
    private List<String> groupList;
    private List<String> roleList;
    private List<SysScope> sysScopeList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
