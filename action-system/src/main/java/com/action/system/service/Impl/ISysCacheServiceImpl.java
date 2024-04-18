package com.action.system.service.Impl;

import com.action.call.clients.RemoteAuthClients;
import com.action.common.common.RedisSetConstants;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.common.Result;
import com.action.common.core.service.RedisCacheServices;
import com.action.system.entity.SysUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 缓存服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/18
 */
@Service
public class ISysCacheServiceImpl {

    @Resource
    private RedisCacheServices redisCacheServices;
    @Resource
    private RemoteAuthClients remoteAuthClients;

    public void setUserExtendCache(String key, SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, Set<BaseSecurityMenu> baseSecurityMenuSet) {
        redisCacheServices.set(key, sysUser);
        redisCacheServices.set(key, groupIdSet);
        redisCacheServices.set(key, postIdSet);
        redisCacheServices.set(key, roleIdSet);
        redisCacheServices.set(key, baseSecurityMenuSet);
    }

    public void setUserExtendCache(SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, Set<BaseSecurityMenu> baseSecurityMenuSet) {
        redisCacheServices.set(RedisSetConstants.USER_BASIS + sysUser.getUsername(), sysUser);
        redisCacheServices.set(RedisSetConstants.USER_GROUP + sysUser.getUsername(), groupIdSet);
        redisCacheServices.set(RedisSetConstants.USER_POST + sysUser.getUsername(), postIdSet);
        redisCacheServices.set(RedisSetConstants.USER_ROLE + sysUser.getUsername(), roleIdSet);
        redisCacheServices.set(RedisSetConstants.USER_MENUPERM + sysUser.getUsername(), baseSecurityMenuSet);
    }

    /*public void cleanUserBasisCache(String key) {
        redisCacheServices.remove(key);
    }

    public void cleanUserBasisCache(String key) {
        redisCacheServices.remove(key);
    }

    public void cleanUserBasisCache(String key) {
        redisCacheServices.remove(key);
    }

    public void cleanUserBasisCache(String key) {
        redisCacheServices.remove(key);
    }*/

    private String getCurrentUserName() {
        Result result = remoteAuthClients.getCurrentUserName();
        if (result.get("code").equals(HttpServletResponse.SC_OK)) {
            return result.get("data").toString();
        }
        return null;
    }
}
