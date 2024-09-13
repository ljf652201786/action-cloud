package com.action.system.manager.service.Impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.service.ISysUserRoleService;
import com.action.system.manager.service.ISysRoleService;
import com.action.system.manager.struct.entity.SysRole;
import com.action.system.manager.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    private final SysRoleMapper sysRoleMapper;
    private final ISysUserRoleService iSysUserRoleService;

    @Override
    public boolean checkRoleCodeExist(String code) {
        return this.getOneOpt(this.getLambdaQueryWrapper().eq(SysRole::getRoleCode, code)).isPresent();
    }

    @Override
    public boolean updateInfo(SysRole sysRole) {
        return this.update(sysRole, this.getLambdaQueryWrapper().eq(SysRole::getId, sysRole.getId()).eq(SysRole::getRoleCode, sysRole.getRoleCode()));
    }

    @Override
    public boolean setDefault(String id, boolean isDefault) {
        return this.update(this.getLambdaUpdateWrapper().set(SysRole::getDefaultRole, isDefault).eq(SysRole::getId, id));
    }

    @Override
    public boolean disable(String id) {
        boolean isDisable = this.update(this.getLambdaUpdateWrapper().set(SysRole::getStatus, StatusType.DISABLED.getStatus()).eq(SysRole::getId, id));
        if (isDisable) {
            iSysUserRoleService.updateRoleStatus(id, StatusType.DISABLED.getStatus());
        }
        return isDisable;
    }

    @Override
    public boolean enable(String id) {
        boolean isEnable = this.update(this.getLambdaUpdateWrapper().set(SysRole::getStatus, StatusType.ENABLE.getStatus()).eq(SysRole::getId, id));
        if (isEnable) {
            iSysUserRoleService.updateRoleStatus(id, StatusType.ENABLE.getStatus());
        }
        return isEnable;
    }
}
