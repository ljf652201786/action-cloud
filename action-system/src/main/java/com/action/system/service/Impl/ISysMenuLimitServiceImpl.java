package com.action.system.service.Impl;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.system.entity.SysMenu;
import com.action.system.entity.SysMenuLimit;
import com.action.system.mapper.SysMenuLimitMapper;
import com.action.system.service.ISysMenuLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 菜单权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysMenuLimitServiceImpl extends ServiceImpl<SysMenuLimitMapper, SysMenuLimit> implements ISysMenuLimitService {
    @Resource
    private SysMenuLimitMapper sysMenuLimitMapper;

    @Override
    public Set<SysMenu> getSysMenuByScope(Set<String> deptIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        return sysMenuLimitMapper.getSysMenuByScope(deptIdSet, postIdSet, roleIdSet);
    }

    @Override
    public Set<BaseSecurityMenu> getBaseSecurityMenuByScope(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        Set<SysMenu> sysMenuSet = this.getSysMenuByScope(groupIdSet, postIdSet, roleIdSet);
        Set<BaseSecurityMenu> baseSecurityMenuSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(sysMenuSet)) {
            baseSecurityMenuSet = sysMenuSet.stream().map(sysMenu -> {
                return new BaseSecurityMenu(sysMenu.getRouteUrl(), sysMenu.getMenuPerm());
            }).collect(Collectors.toSet());
        }
        return baseSecurityMenuSet;
    }
}
