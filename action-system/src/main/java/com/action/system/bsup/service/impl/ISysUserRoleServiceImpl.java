package com.action.system.bsup.service.impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.struct.entity.SysUserRole;
import com.action.system.bsup.mapper.SysUserRoleMapper;
import com.action.system.bsup.service.ICacheService;
import com.action.system.bsup.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<String> getRoleIdSetAndCache(String userId, String username) {
        Set<String> roleIdSet = iCacheService.getUserRoleCache(username);
        if (Objects.nonNull(roleIdSet)) {
            return roleIdSet;
        } else {
            //获取用户角色(已激活的)
            List<SysUserRole> sysUserRoleList = this.getSysUserRoleByUserId(userId);
            if (!CollectionUtils.isEmpty(sysUserRoleList)) {
                roleIdSet = sysUserRoleList.stream().map(sysUserRole -> sysUserRole.getId()).collect(Collectors.toSet());
            }
            iCacheService.setUserRoleCache(username, roleIdSet);
        }
        return roleIdSet;
    }

    @Override
    public boolean saveBatchByUserId(String userId, List<String> roleList) {
        if (!CollectionUtils.isEmpty(roleList)) {
            List<SysUserRole> userRoleList = roleList.stream().map(roleId -> {
                return new SysUserRole(userId, roleId, StatusType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            return this.saveBatch(userRoleList);
        }
        return false;
    }

    //todo 事务
    @Override
    public boolean updateBatchByUserId(String userId, List<String> roleList) {
        this.remove(this.getLambdaQueryWrapper().eq(SysUserRole::getUserId, userId));
        return this.saveBatchByUserId(userId, roleList);
    }

    @Override
    public List<SysUserRole> getSysUserRoleByUserId(String userId) {
        return this.list(this.getLambdaQueryWrapper().eq(SysUserRole::getUserId, userId).eq(SysUserRole::getStatus, StatusType.ENABLE.getStatus()));
    }

    @Override
    public boolean updateRoleStatus(String roleId, String status) {
        boolean isUpdate = this.update(this.getLambdaUpdateWrapper().set(!StringUtils.isEmpty(status), SysUserRole::getStatus, status).eq(SysUserRole::getRoleId, roleId));
        if (isUpdate) {
            iCacheService.cleanRoleCache(roleId);
        }
        return isUpdate;
    }

}
