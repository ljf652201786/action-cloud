package com.action.system.bsup.service.impl;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.enums.StatusType;
import com.action.system.bsup.struct.vo.SysMenuLimitVo;
import com.action.system.manager.struct.dto.PermAllocationDto;
import com.action.system.manager.struct.entity.SysMenu;
import com.action.system.bsup.struct.entity.SysMenuLimit;
import com.action.system.bsup.mapper.SysMenuLimitMapper;
import com.action.system.bsup.service.ISysMenuLimitService;
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
    public void resetMenu(String type, String contactId) {
        this.remove(this.getLambdaQueryWrapper()
                .eq(SysMenuLimit::getType, type)
                .eq(SysMenuLimit::getContactId, contactId));
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

    @Override
    public boolean allocationMenu(PermAllocationDto permAllocationDto) {
        resetMenu(permAllocationDto.getType(), permAllocationDto.getContactId());
        if (!CollectionUtils.isEmpty(permAllocationDto.getMenuIds())) {
            List<SysMenuLimit> sysMenuLimitList = permAllocationDto.getMenuIds().stream().map(menuId -> {
                return new SysMenuLimit(permAllocationDto.getType(), permAllocationDto.getContactId(), menuId, StatusType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            return this.saveBatch(sysMenuLimitList);
        }
        return true;
    }

    @Override
    public List<SysMenuLimitVo> getAllocationMenu(String type, String contactId) {
        return this.selectListBy((e) -> e.list(this.getLambdaQueryWrapper().eq(SysMenuLimit::getType, type).eq(SysMenuLimit::getContactId, contactId)), SysMenuLimitVo.class);
    }
}
