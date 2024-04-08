package com.action.system.service.Impl;

import com.action.system.entity.SysUserRole;
import com.action.system.mapper.SysUserRoleMapper;
import com.action.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

}
