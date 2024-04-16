package com.action.system.service;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;


public interface ISysMenuService extends IService<SysMenu> {
    List<SysMenu> buildMenuTreeSelect();
    Set<? extends BaseSecurityMenu> getSysPermission();
}
