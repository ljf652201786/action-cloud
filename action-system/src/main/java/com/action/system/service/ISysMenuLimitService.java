package com.action.system.service;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.system.entity.SysMenu;
import com.action.system.entity.SysMenuLimit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;


public interface ISysMenuLimitService extends IService<SysMenuLimit> {
    Set<SysMenu> getSysMenuByScope(Set<String> deptIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    Set<BaseSecurityMenu> getBaseSecurityMenuByScope(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);
}
