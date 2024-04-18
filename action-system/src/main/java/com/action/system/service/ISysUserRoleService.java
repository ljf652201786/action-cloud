package com.action.system.service;

import com.action.system.entity.SysRole;
import com.action.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISysUserRoleService extends IService<SysUserRole> {
    List<SysRole> getSysRoleByUserId(String userId);

    List<SysUserRole> getSysUserRoleByUserId(String userId);

    boolean updateRoleStatus(String roleId, String status);
}
