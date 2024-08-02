package com.action.system.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.struct.entity.SysDataColumnLimit;

import java.util.Set;

public interface ISysDataColumnLimitService extends BaseMpService<SysDataColumnLimit> {
    Set<SysDataColumnLimit> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);
}
