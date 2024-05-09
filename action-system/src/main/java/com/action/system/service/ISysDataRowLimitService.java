package com.action.system.service;

import com.action.system.entity.SysDataRowLimit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

public interface ISysDataRowLimitService extends IService<SysDataRowLimit> {
    Set<SysDataRowLimit> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    boolean updateDataRowLimitStatus(String dataRowLimitId, String status);
}
