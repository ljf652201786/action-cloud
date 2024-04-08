package com.action.system.service.Impl;

import com.action.system.entity.SysRole;
import com.action.system.entity.SysUser;
import com.action.system.mapper.SysRoleMapper;
import com.action.system.mapper.SysUserMapper;
import com.action.system.service.ISysRoleService;
import com.action.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

}
