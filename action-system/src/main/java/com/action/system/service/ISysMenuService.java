package com.action.system.service;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.struct.entity.SysMenu;

import java.util.List;
import java.util.Set;


public interface ISysMenuService extends BaseMpService<SysMenu> {

    List<SysMenu> listRoutes();

    Set<? extends BaseSecurityMenu> getSysPermission();
}
