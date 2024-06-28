package com.action.system.service;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.system.entity.SysUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICacheService{
    void setUserDataPermRowCache(String username, Set<DataRowFilterStruct> dataRowFilterStructList);

    void setUserDataPermColumnCache(String username, Map<String, Set<String>> dataColumnFilterMap);

    void setUserExtendInfoCache(String username, SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, List<BaseSecurityMenu> baseSecurityMenuSet);

    void setUserExtendInfoCache(SysUser sysUser, Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet, List<BaseSecurityMenu> baseSecurityMenuSet);

    void setUserBasisCache(String username, SysUser sysUser);

    void setUserMenupermCache(String username, List<BaseSecurityMenu> baseSecurityMenuSet);

    void setUserGroupCache(String username, Set<String> groupIdSet);

    void setUserPostCache(String username, Set<String> postIdSet);

    void setUserRoleCache(String username, Set<String> roleIdSet);

    SysUser getUserBasisCache(String username);

    List<BaseSecurityMenu> getUserMenupermCache(String username);

    Set<String> getUserGroupCache(String username);

    Set<String> getUserPostCache(String username);

    Set<String> getUserRoleCache(String username);

    void cleanUserBasisCache(String username);

    void cleanUserMenupermCache(String username);

    void cleanGroupCache(String groupId);

    void cleanPostCache(Set<String> postIds);

    void cleanPostCache(String postId);

    void cleanRoleCache(String roleId);

    void cleanDataRowLimitCache(String dataRowLimitId);

    void cleanCacheByKey(String... key);
}
