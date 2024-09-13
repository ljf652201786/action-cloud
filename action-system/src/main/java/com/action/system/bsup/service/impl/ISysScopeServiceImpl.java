package com.action.system.bsup.service.impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.struct.entity.SysScope;
import com.action.system.bsup.mapper.SysScopeMapper;
import com.action.system.bsup.service.ICacheService;
import com.action.system.bsup.service.ISysScopeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

    @Override
    public Set<String> getPostIdSetAndCache(String userId, String username) {
        Set<String> postIdSet = iCacheService.getUserPostCache(username);
        if (Objects.nonNull(postIdSet)) {
            return postIdSet;
        } else {
            //获取用户岗位(已激活的)
            List<SysScope> sysScopeList = this.getSysScopeByUserId(userId);
            if (!CollectionUtils.isEmpty(sysScopeList)) {
                postIdSet = sysScopeList.stream().map(sysScope -> sysScope.getPostId()).collect(Collectors.toSet());
            }
            iCacheService.setUserPostCache(username, postIdSet);
        }
        return postIdSet;
    }

    public List<SysScope> getSysScopeByUserId(String userId) {
        return sysScopeMapper.selectList(this.getLambdaQueryWrapper().eq(SysScope::getUserId, userId).eq(SysScope::getDeptStatus, StatusType.ENABLE.getStatus()).eq(SysScope::getPostStatus, StatusType.ENABLE.getStatus()));
    }

    @Override
    public Set<String> saveBatchByUserId(String userId, List<SysScope> scopeList) {
        Set<String> postIdSet = new HashSet<String>();
        if (!CollectionUtils.isEmpty(scopeList)) {
            scopeList.stream().forEach(scope -> {
                postIdSet.add(scope.getPostId());
                scope.setUserId(userId);
                scope.setDeptStatus(StatusType.ENABLE.getStatus());
                scope.setPostStatus(StatusType.ENABLE.getStatus());
            });
            this.saveBatch(scopeList);
        }
        return postIdSet;
    }

    @Override
    public Set<String> updateBatchByUserId(String userId, List<SysScope> scopeList) {
        this.remove(this.getLambdaQueryWrapper(new SysScope()).eq(SysScope::getUserId, userId));
        return this.saveBatchByUserId(userId, scopeList);
    }

    @Override
    public Long deptNum(String deptId) {
        return sysScopeMapper.selectCount(this.getLambdaQueryWrapper().eq(SysScope::getDeptId, deptId));
    }

    @Override
    public Long postNum(String postId) {
        return sysScopeMapper.selectCount(this.getLambdaQueryWrapper().eq(SysScope::getPostId, postId));
    }

    @Override
    public boolean updateDeptStatus(String deptId, String status) {
        List<SysScope> sysScopeList = sysScopeMapper.selectList(this.getLambdaUpdateWrapper().eq(SysScope::getDeptId, deptId).eq(SysScope::getPostStatus, StatusType.ENABLE.getStatus()));
        if (CollectionUtils.isEmpty(sysScopeList)) {
            return true;
        }
        boolean isUpdate = SqlHelper.retBool(sysScopeMapper.update(null, this.getLambdaUpdateWrapper().set(!StringUtils.isEmpty(status), SysScope::getDeptStatus, status).eq(SysScope::getDeptId, deptId)));
        if (isUpdate) {
            Set<String> postIds = sysScopeList.stream().map(sysScope -> sysScope.getPostId()).collect(Collectors.toSet());
            iCacheService.cleanPostCache(postIds);
        }
        return isUpdate;
    }

    @Override
    public boolean updatePostStatus(String postId, String status) {
        boolean isUpdate = this.update(this.getLambdaUpdateWrapper().set(!StringUtils.isEmpty(status), SysScope::getPostStatus, status).eq(SysScope::getPostId, postId));
        if (isUpdate) {
            iCacheService.cleanPostCache(postId);
        }
        return isUpdate;
    }
}
