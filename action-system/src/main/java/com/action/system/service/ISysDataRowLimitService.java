package com.action.system.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.struct.entity.SysDataRowLimit;

import java.util.Set;

public interface ISysDataRowLimitService extends BaseMpService<SysDataRowLimit> {
    Set<SysDataRowLimit> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    boolean updateDataRowLimitStatus(String dataRowLimitId, String status);
}
