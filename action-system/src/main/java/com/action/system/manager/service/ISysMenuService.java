package com.action.system.manager.service;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.entity.SysMenu;

import java.util.List;
import java.util.Set;


public interface ISysMenuService extends BaseMpService<SysMenu> {

    List<SysMenu> listRoutes();

    Set<? extends BaseSecurityMenu> getSysPermission();

    boolean disable(String menuId);

    boolean enable(String menuId);
}
