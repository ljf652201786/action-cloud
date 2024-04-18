package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysUserGroup;
import com.action.system.mapper.SysUserGroupMapper;
import com.action.system.service.ISysUserGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 用户用户组中间表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysUserGroupServiceImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroup> implements ISysUserGroupService {
    @Resource
    private SysUserGroupMapper sysUserGroupMapper;

    @Override
    public List<SysUserGroup> getSysUserGroupByUserId(String userId) {
        return sysUserGroupMapper.selectList(new QueryWrapper<SysUserGroup>().eq("user_id", userId).eq("status", UseType.ENABLE.getStatus()));
    }

    @Override
    public boolean updateGroupStatus(String groupId, String status) {
        return SqlHelper.retBool(sysUserGroupMapper.update(new QueryWrapper<SysUserGroup>().eq("group_id", groupId).eq("status", status)));
    }
}
