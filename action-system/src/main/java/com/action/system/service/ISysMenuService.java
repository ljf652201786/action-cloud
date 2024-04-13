package com.action.system.service;

import com.action.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ISysMenuService extends IService<SysMenu> {
    List<SysMenu> buildMenuTreeSelect();
}
