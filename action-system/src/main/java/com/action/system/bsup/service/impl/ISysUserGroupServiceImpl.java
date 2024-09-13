package com.action.system.bsup.service.impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.struct.entity.SysUserGroup;
import com.action.system.bsup.mapper.SysUserGroupMapper;
import com.action.system.bsup.service.ICacheService;
import com.action.system.bsup.service.ISysUserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<String> getGroupIdSetAndCache(String userId, String username) {
        Set<String> groupIdSet = iCacheService.getUserGroupCache(username);
        if (Objects.nonNull(groupIdSet)) {
            return groupIdSet;
        } else {
            //获取用户组(已激活的)
            List<SysUserGroup> sysUserGroupList = this.getSysUserGroupByUserId(userId);
            if (!CollectionUtils.isEmpty(sysUserGroupList)) {
                groupIdSet = sysUserGroupList.stream().map(sysUserGroup -> sysUserGroup.getGroupId()).collect(Collectors.toSet());
            }
            iCacheService.setUserGroupCache(username, groupIdSet);
        }
        return groupIdSet;
    }

    @Override
    public List<SysUserGroup> getSysUserGroupByUserId(String userId) {
        return this.list(this.getLambdaQueryWrapper().eq(SysUserGroup::getUserId, userId).eq(SysUserGroup::getStatus, StatusType.ENABLE.getStatus()));
    }

    @Override
    public boolean saveBatchByUserId(String userId, List<String> groupList) {
        if (!CollectionUtils.isEmpty(groupList)) {
            List<SysUserGroup> userGroupList = groupList.stream().map(groupId -> {
                return new SysUserGroup(userId, groupId, StatusType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            return this.saveBatch(userGroupList);
        }
        return false;
    }

    @Override
    public boolean updateBatchByUserId(String userId, List<String> groupList) {
        this.remove(this.getLambdaQueryWrapper().eq(SysUserGroup::getUserId, userId));
        return saveBatchByUserId(userId, groupList);
    }

    @Override
    public boolean updateGroupStatus(String groupId, String status) {
        boolean isUpdate = this.update(this.getLambdaUpdateWrapper().set(StringUtils.isNoneBlank(status), SysUserGroup::getStatus, status).eq(SysUserGroup::getGroupId, groupId));
        if (isUpdate) {
            iCacheService.cleanGroupCache(groupId);
        }
        return isUpdate;
    }
}
