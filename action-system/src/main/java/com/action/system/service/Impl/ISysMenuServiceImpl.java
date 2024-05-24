package com.action.system.service.Impl;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.enums.UseType;
import com.action.system.entity.SysMenu;
import com.action.system.enums.MenuType;
import com.action.system.enums.NodeType;
import com.action.system.mapper.SysMenuMapper;
import com.action.system.service.ISysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 菜单表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService{
    @Resource
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> buildMenuTreeSelect() {
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(new QueryWrapper<>());//条件搜索
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return List.of();
        }
        List<SysMenu> parentMenuList = sysMenuList.stream().filter(menu -> menu.getParentId().equals(NodeType.PARENT.getType()) && menu.getMenuType().equalsIgnoreCase(MenuType.LIST.getType())).collect(Collectors.toList());
        parentMenuList.stream().forEach(parentMenu -> {
            List<SysMenu> childrenDeptList = new ArrayList<>();
            parentMenu.setChildrenList(childrenDeptList);
            this.buildMenuTree(sysMenuList, childrenDeptList, parentMenu.getId());
        });
        return parentMenuList;
    }

    @Override
    public Set<? extends BaseSecurityMenu> getSysPermission() {
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getStatus, UseType.ENABLE.getStatus()));
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return new HashSet<>();
        }
        Set<BaseSecurityMenu> securityMenuSet = sysMenuList.stream().map(sysMenu -> {
            return new BaseSecurityMenu(sysMenu.getId(), sysMenu.getRouteUrl(), sysMenu.getMenuPerm());
        }).collect(Collectors.toSet());
        return securityMenuSet;
    }

    private void buildMenuTree(List<SysMenu> menus, List<SysMenu> childrenDeptList, String parentId) {
        menus.stream().forEach(menu -> {
            if (menu.getParentId().equals(parentId)) {
                List<SysMenu> childrenMenuList1 = new ArrayList<>();
                menu.setChildrenList(childrenMenuList1);
                childrenDeptList.add(menu);
                this.buildMenuTree(menus, childrenMenuList1, menu.getId());
            }
        });
    }
}
