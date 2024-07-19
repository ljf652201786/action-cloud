package com.action.system.service;

import com.action.system.struct.entity.SysUserGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISysUserGroupService extends IService<SysUserGroup> {
    List<SysUserGroup> getSysUserGroupByUserId(String userId);

    boolean updateGroupStatus(String groupId, String status);
}
