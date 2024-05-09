package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysScope;
import com.action.system.mapper.SysScopeMapper;
import com.action.system.service.ICacheService;
import com.action.system.service.ISysScopeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 范围表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysScopeServiceImpl extends ServiceImpl<SysScopeMapper, SysScope> implements ISysScopeService {
    @Resource
    private SysScopeMapper sysScopeMapper;
    @Resource
    private ICacheService iCacheService;

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
        List<SysScope> sysScopeList = sysScopeMapper.selectList(new QueryWrapper<SysScope>().eq("dept_id", deptId).eq("post_status", UseType.ENABLE.getStatus()));
        if (CollectionUtils.isEmpty(sysScopeList)) {
            return true;
        }
        boolean isUpdate = SqlHelper.retBool(sysScopeMapper.update(null, new UpdateWrapper<SysScope>().set(!StringUtils.isEmpty(status), "dept_status", status).eq("dept_id", deptId)));
        if (isUpdate) {
            Set<String> postIds = sysScopeList.stream().map(sysScope -> sysScope.getPostId()).collect(Collectors.toSet());
            iCacheService.cleanPostCache(postIds);
        }
        return isUpdate;
    }

    @Override
    public boolean updatePostStatus(String postId, String status) {
        boolean isUpdate = SqlHelper.retBool(sysScopeMapper.update(null, new UpdateWrapper<SysScope>().set(!StringUtils.isEmpty(status), "post_status", status).eq("post_id", postId)));
        if (isUpdate) {
            iCacheService.cleanPostCache(postId);
        }
        return isUpdate;
    }
}
