package com.action.system.service.Impl;

import com.action.common.enums.StatusType;
import com.action.system.struct.entity.SysUserGroup;
import com.action.system.mapper.SysUserGroupMapper;
import com.action.system.service.ICacheService;
import com.action.system.service.ISysUserGroupService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Description: 用户用户组中间表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysUserGroupServiceImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroup> implements ISysUserGroupService {
    private final SysUserGroupMapper sysUserGroupMapper;
    private final ICacheService iCacheService;

    @Override
    public List<SysUserGroup> getSysUserGroupByUserId(String userId) {
        return sysUserGroupMapper.selectList(Wrappers.<SysUserGroup>lambdaQuery().eq(SysUserGroup::getUserId, userId).eq(SysUserGroup::getStatus, StatusType.ENABLE.getStatus()));
    }

    @Override
    public boolean updateGroupStatus(String groupId, String status) {
        boolean isUpdate = SqlHelper.retBool(sysUserGroupMapper.update(Wrappers.<SysUserGroup>lambdaUpdate().set(!StringUtils.isEmpty(status), SysUserGroup::getStatus, status).eq(SysUserGroup::getGroupId, groupId)));
        if (isUpdate) {
            iCacheService.cleanGroupCache(groupId);
        }
        return isUpdate;
    }
}
