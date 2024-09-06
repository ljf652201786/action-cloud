package com.action.system.service.Impl;

import com.action.common.common.RedisSetConstants;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.constants.StringPool;
import com.action.common.core.service.RedisCacheServices;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.system.struct.entity.SysUser;
import com.action.system.service.ICacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.action.common.core.constants.ActionRedisConstants.DATAPERM_COLUMN_KEY;
import static com.action.common.core.constants.ActionRedisConstants.DATAPERM_ROW_KEY;

/**
 * @Description: 缓存服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/18
 */
@Service
@RequiredArgsConstructor
public class ISysCacheServiceImpl implements ICacheService {
    private final RedisCacheServices redisCacheServices;

    @Override
    public void setUserDataPermRowCache(String username, Set<DataRowFilterStruct> dataRowFilterStructList) {
        redisCacheServices.set(DATAPERM_ROW_KEY + username, dataRowFilterStructList);
    }

    @Override
    public void setUserDataPermColumnCache(String username, Map<String, Set<String>> dataColumnFilterMap) {
        redisCacheServices.set(DATAPERM_COLUMN_KEY + username, dataColumnFilterMap);
    }

    @Override
    public void setUserExtendInfoCache(String username, SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, List<BaseSecurityMenu> baseSecurityMenuSet) {
        this.setUserBasisCache(username, sysUser);
        this.setUserGroupCache(username, groupIdSet);
        this.setUserPostCache(username, postIdSet);
        this.setUserRoleCache(username, roleIdSet);
        this.setUserMenupermCache(username, baseSecurityMenuSet);
    }

    @Override
    public void setUserExtendInfoCache(SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, List<BaseSecurityMenu> baseSecurityMenuSet) {
        this.setUserBasisCache(sysUser.getUsername(), sysUser);
        this.setUserGroupCache(sysUser.getUsername(), groupIdSet);
        this.setUserPostCache(sysUser.getUsername(), postIdSet);
        this.setUserRoleCache(sysUser.getUsername(), roleIdSet);
        this.setUserMenupermCache(sysUser.getUsername(), baseSecurityMenuSet);
    }

    public void setUserBasisCache(String username, SysUser sysUser) {
        redisCacheServices.set(RedisSetConstants.USER_BASIS + username, sysUser);
    }

    public void setUserMenupermCache(String username, List<BaseSecurityMenu> baseSecurityMenuSet) {
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
    public List<BaseSecurityMenu> getUserMenupermCache(String username) {
        Object o = redisCacheServices.get(RedisSetConstants.USER_MENUPERM + username);
        if (Objects.isNull(o)) {
            return null;
        }
        return (List<BaseSecurityMenu>) o;
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
        this.cleanCacheByKey(RedisSetConstants.USER_BASIS + username);
    }

    @Override
    public void cleanUserMenupermCache(String username) {
        this.cleanCacheByKey(RedisSetConstants.USER_MENUPERM + username);
    }

    @Override
    public void cleanGroupCache(String groupId) {
        String username = this.removeKeyByIdInValueReturnUsername(RedisSetConstants.USER_GROUP, groupId, (o) -> (Set<String>) o);
        this.cleanUserMenupermCache(username);
    }

    @Override
    public void cleanPostCache(Set<String> postIds) {
        String username = this.removeKeyByIdInValueReturnUsername(RedisSetConstants.USER_POST, String.join(StringPool.COMMA, postIds), (o) -> (Set<String>) o);
        this.cleanUserMenupermCache(username);
    }

    @Override
    public void cleanPostCache(String postId) {
        String username = this.removeKeyByIdInValueReturnUsername(RedisSetConstants.USER_POST, postId, (o) -> (Set<String>) o);
        this.cleanUserMenupermCache(username);
    }

    @Override
    public void cleanRoleCache(String roleId) {
        String username = this.removeKeyByIdInValueReturnUsername(RedisSetConstants.USER_ROLE, roleId, (o) -> (Set<String>) o);
        this.cleanUserMenupermCache(username);
    }

    @Override
    public void cleanDataRowLimitCache(String dataRowLimitId) {
        this.removeKeyByIdInValueReturnUsername(DATAPERM_ROW_KEY, dataRowLimitId, (o) -> getDataRowCacheObjectId(o));
    }

    @Override
    public void cleanCacheByKey(String... key) {
        Arrays.stream(key).filter(k -> StringUtils.isNotEmpty(k)).forEach(k -> redisCacheServices.remove(k));
    }

    private String removeKeyByIdInValueReturnUsername(String pattern, String ids, Function<Object, Set<String>> fun) {
        AtomicReference<String> username = new AtomicReference<>(StringPool.EMPTY);
        List<String> keylist = redisCacheServices.keysAfter(pattern);
        keylist.stream().forEach(key -> {
            Object o = redisCacheServices.get(key);
            if (Objects.nonNull(o)) {
                Set<String> idSet = fun.apply(o);
                Set<String> idList = Arrays.stream(ids.split(StringPool.COMMA)).collect(Collectors.toSet());
                if (CollectionUtils.containsAny(idSet, idList)) {
                    this.cleanCacheByKey(key);
                    username.set(key.substring(key.lastIndexOf(StringPool.COLON) + 1));
                }
            }
        });
        return username.get();
    }

    private Set<String> getDataRowCacheObjectId(Object o) {
        Set<String> idSet = ((Set<DataRowFilterStruct>) o).stream().map(dataRowFilterStruct -> dataRowFilterStruct.getId()).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(idSet)) {
            return new HashSet<>();
        }
        return idSet;
    }
}
