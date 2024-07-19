package com.action.system.service.Impl;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.system.struct.entity.SysMenu;
import com.action.system.struct.entity.SysMenuLimit;
import com.action.system.mapper.SysMenuLimitMapper;
import com.action.system.service.ISysMenuLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 菜单权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysMenuLimitServiceImpl extends ServiceImpl<SysMenuLimitMapper, SysMenuLimit> implements ISysMenuLimitService {
    private final SysMenuLimitMapper sysMenuLimitMapper;

    @Override
    public Set<SysMenu> getSysMenuByScope(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        return sysMenuLimitMapper.getSysMenuByScope(groupIdSet, postIdSet, roleIdSet);
    }

    @Override
    public List<BaseSecurityMenu> getBaseSecurityMenuByScope(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        Set<SysMenu> sysMenuSet = this.getSysMenuByScope(groupIdSet, postIdSet, roleIdSet);
        List<BaseSecurityMenu> baseSecurityMenuSet = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysMenuSet)) {
            baseSecurityMenuSet = sysMenuSet.stream().map(sysMenu -> {
                return new BaseSecurityMenu(sysMenu.getId(), sysMenu.getRouteUrl(), sysMenu.getMenuPerm());
            }).collect(Collectors.toList());
        }
        return baseSecurityMenuSet;
    }
}
