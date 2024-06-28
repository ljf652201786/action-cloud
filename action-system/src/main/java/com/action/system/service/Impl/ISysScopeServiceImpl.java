package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysScope;
import com.action.system.mapper.SysScopeMapper;
import com.action.system.service.ICacheService;
import com.action.system.service.ISysScopeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ISysScopeServiceImpl extends ServiceImpl<SysScopeMapper, SysScope> implements ISysScopeService {
    private final SysScopeMapper sysScopeMapper;
    private final ICacheService iCacheService;

    public List<SysScope> getSysScopeByUserId(String userId) {
        return sysScopeMapper.selectList(Wrappers.<SysScope>lambdaQuery().eq(SysScope::getUserId, userId).eq(SysScope::getDeptStatus, UseType.ENABLE.getStatus()).eq(SysScope::getPostStatus, UseType.ENABLE.getStatus()));
    }

    @Override
    public Long deptNum(String deptId) {
        return sysScopeMapper.selectCount(Wrappers.<SysScope>lambdaQuery().eq(SysScope::getDeptId, deptId));
    }

    @Override
    public Long postNum(String deptId) {
        return sysScopeMapper.selectCount(Wrappers.<SysScope>lambdaQuery().eq(SysScope::getPostId, deptId));
    }

    @Override
    public boolean updateDeptStatus(String deptId, String status) {
        List<SysScope> sysScopeList = sysScopeMapper.selectList(Wrappers.<SysScope>lambdaQuery().eq(SysScope::getDeptId, deptId).eq(SysScope::getPostStatus, UseType.ENABLE.getStatus()));
        if (CollectionUtils.isEmpty(sysScopeList)) {
            return true;
        }
        boolean isUpdate = SqlHelper.retBool(sysScopeMapper.update(null, Wrappers.<SysScope>lambdaUpdate().set(!StringUtils.isEmpty(status), SysScope::getDeptStatus, status).eq(SysScope::getDeptId, deptId)));
        if (isUpdate) {
            Set<String> postIds = sysScopeList.stream().map(sysScope -> sysScope.getPostId()).collect(Collectors.toSet());
            iCacheService.cleanPostCache(postIds);
        }
        return isUpdate;
    }

    @Override
    public boolean updatePostStatus(String postId, String status) {
        boolean isUpdate = SqlHelper.retBool(sysScopeMapper.update(null, Wrappers.<SysScope>lambdaUpdate().set(!StringUtils.isEmpty(status), SysScope::getPostStatus, status).eq(SysScope::getPostId, postId)));
        if (isUpdate) {
            iCacheService.cleanPostCache(postId);
        }
        return isUpdate;
    }
}
