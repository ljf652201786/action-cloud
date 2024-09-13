package com.action.system.bsup.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.bsup.struct.entity.SysUserGroup;

import java.util.List;
import java.util.Set;

public interface ISysUserGroupService extends BaseMpService<SysUserGroup> {

    Set<String> getGroupIdSetAndCache(String userId, String username);

    List<SysUserGroup> getSysUserGroupByUserId(String userId);

    boolean saveBatchByUserId(String userId, List<String> groupList);

    boolean updateBatchByUserId(String userId, List<String> groupList);

    boolean updateGroupStatus(String groupId, String status);
}
