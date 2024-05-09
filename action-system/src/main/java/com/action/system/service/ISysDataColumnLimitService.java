package com.action.system.service;

import com.action.system.entity.SysDataColumnLimit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

public interface ISysDataColumnLimitService extends IService<SysDataColumnLimit> {
    Set<SysDataColumnLimit> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);
}
