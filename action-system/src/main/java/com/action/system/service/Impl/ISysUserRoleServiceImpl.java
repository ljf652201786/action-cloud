package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysRole;
import com.action.system.entity.SysUserRole;
import com.action.system.mapper.SysUserRoleMapper;
import com.action.system.service.ICacheService;
import com.action.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    private final SysUserRoleMapper sysUserRoleMapper;
    private final ICacheService iCacheService;

    @Override
    public List<SysRole> getSysRoleByUserId(String userId) {
        return sysUserRoleMapper.getSysRoleByUserId(userId);
    }

    @Override
    public List<SysUserRole> getSysUserRoleByUserId(String userId) {
        return sysUserRoleMapper.selectList(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId).eq(SysUserRole::getStatus, UseType.ENABLE.getStatus()));
    }

    @Override
    public boolean updateRoleStatus(String roleId, String status) {
        boolean isUpdate = SqlHelper.retBool(sysUserRoleMapper.update(Wrappers.<SysUserRole>lambdaUpdate().set(!StringUtils.isEmpty(status), SysUserRole::getStatus, status).eq(SysUserRole::getRoleId, roleId)));
        if (isUpdate) {
            iCacheService.cleanRoleCache(roleId);
        }
        return isUpdate;
    }

}
