package com.action.system.service.Impl;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.constants.StringPool;
import com.action.common.enums.UseType;
import com.action.common.security.util.SecurityUtil;
import com.action.system.mapper.SysMenuLimitMapper;
import com.action.system.service.ICacheService;
import com.action.system.entity.SysMenu;
import com.action.system.enums.MenuType;
import com.action.system.enums.NodeType;
import com.action.system.mapper.SysMenuMapper;
import com.action.system.service.ISysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 菜单表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    private final SysMenuMapper sysMenuMapper;
    private final SysMenuLimitMapper sysMenuLimitMapper;
    private final ICacheService iCacheService;
    private final SecurityUtil securityUtil;


    @Override
    public List<SysMenu> listRoutes() {
        String userName = securityUtil.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return List.of();
        }
        Set<String> userGroupCache = iCacheService.getUserGroupCache(userName);
        Set<String> userPostCache = iCacheService.getUserPostCache(userName);
        Set<String> userRoleCache = iCacheService.getUserRoleCache(userName);
        Set<SysMenu> sysMenuByScope = sysMenuLimitMapper.getSysMenuByScope(userGroupCache, userPostCache, userRoleCache);
        if (CollectionUtils.isEmpty(sysMenuByScope)) {
            return List.of();
        }
        List<String> ancestralMenuIdList = new ArrayList<>();
        sysMenuByScope.stream()
                .filter(sysMenu -> !sysMenu.getMenuType().equalsIgnoreCase(MenuType.BUTTON.getType()) || StringUtils.isNotEmpty(sysMenu.getAncestral()))
                .forEach(sysMenu -> {
                    String[] ancestralArray = sysMenu.getAncestral().split(StringPool.SLASH);
                    ancestralMenuIdList.addAll(Arrays.stream(ancestralArray).toList());
                });
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().lambda().in(SysMenu::getId, ancestralMenuIdList));
        sysMenuByScope.addAll(sysMenuList);
        return buildMenu(sysMenuByScope.stream(), sysMenuByScope.stream().toList());
    }

    @Override
    public List<SysMenu> buildMenuTreeSelect() {
        List<SysMenu> sysMenuList = this.list();
        return buildMenu(sysMenuList.stream(), sysMenuList);
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

    @NotNull
    private List<SysMenu> buildMenu(@NotNull Stream<SysMenu> sysMenuStream, List<SysMenu> sysMenuList) {
        List<SysMenu> parentMenuList = sysMenuStream.filter(menu -> menu.getParentId().equals(NodeType.PARENT.getType()) && menu.getMenuType().equalsIgnoreCase(MenuType.LIST.getType())).collect(Collectors.toList());
        parentMenuList.stream().forEach(parentMenu -> {
            List<SysMenu> childrenDeptList = new ArrayList<>();
            parentMenu.setChildrenList(childrenDeptList);
            this.buildMenuTree(sysMenuList, childrenDeptList, parentMenu.getId());
        });
        return parentMenuList;
    }

    private void buildMenuTree(@NotNull List<SysMenu> menus, List<SysMenu> childrenDeptList, String parentId) {
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
