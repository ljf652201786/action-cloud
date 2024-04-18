package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysScope;
import com.action.system.mapper.SysScopeMapper;
import com.action.system.service.ISysScopeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 范围表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysScopeServiceImpl extends ServiceImpl<SysScopeMapper, SysScope> implements ISysScopeService {
    @Resource
    private SysScopeMapper sysScopeMapper;

    public List<SysScope> getSysScopeByUserId(String userId) {
        return sysScopeMapper.selectList(new QueryWrapper<SysScope>().eq("user_id", userId).eq("dept_status", UseType.ENABLE.getStatus()).eq("post_status", UseType.ENABLE.getStatus()));
    }

    @Override
    public Long deptNum(String deptId) {
        return sysScopeMapper.selectCount(new QueryWrapper<SysScope>().eq("dept_id", deptId));
    }

    @Override
    public Long postNum(String deptId) {
        return sysScopeMapper.selectCount(new QueryWrapper<SysScope>().eq("post_id", deptId));
    }

    @Override
    public boolean updateDeptStatus(String deptId, String status) {
        return SqlHelper.retBool(sysScopeMapper.update(new QueryWrapper<SysScope>().eq("dept_id", deptId).eq("status", status)));
    }

    @Override
    public boolean updatePostStatus(String postId, String status) {
        return SqlHelper.retBool(sysScopeMapper.update(new QueryWrapper<SysScope>().eq("post_id", postId).eq("status", status)));
    }
}
