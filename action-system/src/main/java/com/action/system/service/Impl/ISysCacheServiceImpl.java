package com.action.system.service.Impl;

import com.action.common.common.RedisSetConstants;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.constants.StringPool;
import com.action.common.core.service.RedisCacheServices;
import com.action.system.entity.SysUser;
import com.action.system.service.ICacheService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Description: 缓存服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/18
 */
@Service
public class ISysCacheServiceImpl implements ICacheService {
    @Resource
    private RedisCacheServices redisCacheServices;

    @Override
    public void setUserExtendInfoCache(String username, SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, Set<BaseSecurityMenu> baseSecurityMenuSet) {
        this.setUserBasisCache(username, sysUser);
        this.setUserGroupCache(username, groupIdSet);
        this.setUserPostCache(username, postIdSet);
        this.setUserRoleCache(username, roleIdSet);
        this.setUserMenupermCache(username, baseSecurityMenuSet);
    }

    @Override
    public void setUserExtendInfoCache(SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, Set<BaseSecurityMenu> baseSecurityMenuSet) {
        this.setUserBasisCache(sysUser.getUsername(), sysUser);
        this.setUserGroupCache(sysUser.getUsername(), groupIdSet);
        this.setUserPostCache(sysUser.getUsername(), postIdSet);
        this.setUserRoleCache(sysUser.getUsername(), roleIdSet);
        this.setUserMenupermCache(sysUser.getUsername(), baseSecurityMenuSet);
    }

    public void setUserBasisCache(String username, SysUser sysUser) {
        redisCacheServices.set(RedisSetConstants.USER_BASIS + username, sysUser);
    }

    public void setUserMenupermCache(String username, Set<BaseSecurityMenu> baseSecurityMenuSet) {
        redisCacheServices.set(RedisSetConstants.USER_MENUPERM + username, baseSecurityMenuSet);
    }

    @Override
    public void setUserGroupCache(String username, Set<String> groupIdSet) {
        redisCacheServices.set(RedisSetConstants.USER_GROUP + username, groupIdSet);
    }

    @Override
    public void setUserPostCache(String username, Set<String> postIdSet) {
        redisCacheServices.set(RedisSetConstants.USER_POST + username, postIdSet);
    }

    @Override
    public void setUserRoleCache(String username, Set<String> roleIdSet) {
        redisCacheServices.set(RedisSetConstants.USER_ROLE + username, roleIdSet);
    }

    @Override
    public SysUser getUserBasisCache(String username) {
        Object o = redisCacheServices.get(RedisSetConstants.USER_BASIS + username);
        if (Objects.isNull(o)) {
            return null;
        }
        return (SysUser) o;
    }

    @Override
    public Set<BaseSecurityMenu> getUserMenupermCache(String username) {
        Object o = redisCacheServices.get(RedisSetConstants.USER_MENUPERM + username);
        if (Objects.isNull(o)) {
            return null;
        }
        return (Set<BaseSecurityMenu>) o;
    }

    @Override
    public Set<String> getUserGroupCache(String username) {
        Object o = redisCacheServices.get(RedisSetConstants.USER_GROUP + username);
        if (Objects.isNull(o)) {
            return null;
        }
        return (Set<String>) o;
    }

    @Override
    public Set<String> getUserPostCache(String username) {
        Object o = redisCacheServices.get(RedisSetConstants.USER_POST + username);
        if (Objects.isNull(o)) {
            return null;
        }
        return (Set<String>) o;
    }

    @Override
    public Set<String> getUserRoleCache(String username) {
        Object o = redisCacheServices.get(RedisSetConstants.USER_ROLE + username);
        if (Objects.isNull(o)) {
            return null;
        }
        return (Set<String>) o;
    }

    @Override
    public void cleanUserBasisCache(String username) {
        redisCacheServices.remove(RedisSetConstants.USER_BASIS + username);
    }

    @Override
    public void cleanUserMenupermCache(String username) {
        redisCacheServices.remove(RedisSetConstants.USER_MENUPERM + username);
    }

    @Override
    public void cleanUserGroupCache(String groupId) {
        String username = this.removeKeyByIdInValueReturnUsername(RedisSetConstants.USER_GROUP, groupId);
        this.cleanUserMenupermCache(username);
    }

    @Override
    public void cleanUserPostCache(Set<String> postIds) {
        String username = this.removeKeyByIdInValueReturnUsername(RedisSetConstants.USER_POST, String.join(StringPool.COMMA, postIds));
        this.cleanUserMenupermCache(username);
    }

    @Override
    public void cleanUserPostCache(String postId) {
        String username = this.removeKeyByIdInValueReturnUsername(RedisSetConstants.USER_POST, postId);
        this.cleanUserMenupermCache(username);
    }

    @Override
    public void cleanUserRoleCache(String roleId) {
        String username = this.removeKeyByIdInValueReturnUsername(RedisSetConstants.USER_ROLE, roleId);
        this.cleanUserMenupermCache(username);
    }

    private String removeKeyByIdInValueReturnUsername(String pattern, String ids) {
        AtomicReference<String> username = new AtomicReference<>(StringPool.EMPTY);
        List<String> keylist = redisCacheServices.keysAfter(pattern);
        keylist.stream().forEach(key -> {
            Object o = redisCacheServices.get(key);
            if (Objects.nonNull(o)) {
                Set<String> idSet = (Set<String>) o;
                Set<String> idList = Arrays.stream(ids.split(StringPool.COMMA)).collect(Collectors.toSet());
                if (CollectionUtils.containsAny(idSet, idList)) {
                    redisCacheServices.remove(key);
                    username.set(key.substring(key.lastIndexOf(StringPool.COLON) + 1));
                }
            }
        });
        return username.get();
    }
}
