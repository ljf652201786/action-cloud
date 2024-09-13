package com.action.system.bsup.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.entity.SysRole;
import com.action.system.bsup.struct.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

public interface ISysUserRoleService extends BaseMpService<SysUserRole> {

    Set<String> getRoleIdSetAndCache(String userId, String username);

    boolean saveBatchByUserId(String userId, List<String> roleList);

    boolean updateBatchByUserId(String userId, List<String> roleList);

    List<SysUserRole> getSysUserRoleByUserId(String userId);

    boolean updateRoleStatus(String roleId, String status);
}
