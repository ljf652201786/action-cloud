package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysRole;
import com.action.system.entity.SysUserRole;
import com.action.system.mapper.SysUserRoleMapper;
import com.action.system.service.ICacheService;
import com.action.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private ICacheService iCacheService;

    @Override
    public List<SysRole> getSysRoleByUserId(String userId) {
        return sysUserRoleMapper.getSysRoleByUserId(userId);
    }

    @Override
    public List<SysUserRole> getSysUserRoleByUserId(String userId) {
        return sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id", userId).eq("status", UseType.ENABLE.getStatus()));
    }

    @Override
    public boolean updateRoleStatus(String roleId, String status) {
        boolean isUpdate = SqlHelper.retBool(sysUserRoleMapper.update(new UpdateWrapper<SysUserRole>().set(!StringUtils.isEmpty(status), "status", status).eq("role_id", roleId)));
        if (isUpdate) {
            iCacheService.cleanRoleCache(roleId);
        }
        return isUpdate;
    }

}
