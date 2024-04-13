package com.action.system.service.Impl;

import com.action.system.entity.SysMenu;
import com.action.system.enums.MenuType;
import com.action.system.enums.NodeType;
import com.action.system.mapper.SysMenuMapper;
import com.action.system.service.ISysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 菜单表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
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
