package com.action.system.service.Impl;

import com.action.system.entity.SysMenu;
import com.action.system.entity.SysMenuLimit;
import com.action.system.mapper.SysMenuLimitMapper;
import com.action.system.service.ISysMenuLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Description: 菜单权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysMenuLimitServiceImpl extends ServiceImpl<SysMenuLimitMapper, SysMenuLimit> implements ISysMenuLimitService {
    @Resource
    private SysMenuLimitMapper sysMenuLimitMapper;

    public Set<SysMenu> getSysMenuByScope(Set<String> deptIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        return sysMenuLimitMapper.getSysMenuByScope(deptIdSet, postIdSet, roleIdSet);
    }
}
