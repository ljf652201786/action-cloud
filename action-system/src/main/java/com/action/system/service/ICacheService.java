package com.action.system.service;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.system.entity.SysUser;

import java.util.List;
import java.util.Set;

public interface ICacheService {
    void setUserExtendInfoCache(String username, SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, Set<BaseSecurityMenu> baseSecurityMenuSet);

    void setUserExtendInfoCache(SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, Set<BaseSecurityMenu> baseSecurityMenuSet);

    void setUserBasisCache(String username, SysUser sysUser);

    void setUserMenupermCache(String username, Set<BaseSecurityMenu> baseSecurityMenuSet);

    void setUserGroupCache(String username, Set<String> groupIdSet);

    void setUserPostCache(String username, Set<String> postIdSet);

    void setUserRoleCache(String username, Set<String> roleIdSet);

    SysUser getUserBasisCache(String username);

    Set<BaseSecurityMenu> getUserMenupermCache(String username);

    Set<String> getUserGroupCache(String username);

    Set<String> getUserPostCache(String username);

    Set<String> getUserRoleCache(String username);

    void cleanUserBasisCache(String username);

    void cleanUserMenupermCache(String username);

    void cleanUserGroupCache(String username);

    void cleanUserPostCache(Set<String> postIds);

    void cleanUserPostCache(String username);

    void cleanUserRoleCache(String username);
}
