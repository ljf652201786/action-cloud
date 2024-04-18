package com.action.system.service.Impl;

import com.action.call.clients.RemoteAuthClients;
import com.action.common.common.RedisSetConstants;
import com.action.common.common.UserSetConstants;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.common.Result;
import com.action.common.entity.SecurityAuthUser;
import com.action.common.enums.UseType;
import com.action.system.dto.SysUserExtend;
import com.action.system.entity.*;
import com.action.system.mapper.*;
import com.action.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private ISysMenuLimitService iSysMenuLimitService;
    @Resource
    private ISysScopeService iSysScopeService;
    @Resource
    private ISysRoleService iSysRoleService;
    @Resource
    private ISysUserRoleService iSysUserRoleService;
    @Resource
    private ISysUserGroupService iSysUserGroupService;
    @Resource
    private RemoteAuthClients authClients;
    @Resource
    private ICacheService iCacheService;

    @Override
    public Boolean regist(SysUserExtend sysUser) {
        Result result = authClients.generatePwd(sysUser.getPassword());
        String enablePwd = result.get("data").toString();
        if (StringUtils.isEmpty(enablePwd)) {
            return false;
        }
        sysUser.setPassword(enablePwd);
        sysUser.setAvatar(UserSetConstants.DEFAULT_AVATAR);
        boolean isSave = SqlHelper.retBool(sysUserMapper.insert(sysUser));
        return isSave;
    }

    @Override
    public Boolean editInfo(SysUser sysUser) {
        SysUser u = sysUserMapper.selectById(sysUser.getId());
        if (Objects.isNull(u)) {
            return false;
        }
        sysUser.setUsername(u.getUsername());//用户名不允许修改
        sysUser.setPassword(u.getPassword());//密码不允许修改
        return SqlHelper.retBool(sysUserMapper.updateById(u));
    }

    @Override
    public void setUserDefaultRole(SysUserExtend sysUserExtend) {
        List<SysRole> roleList = iSysRoleService.listObjs(new QueryWrapper<SysRole>().eq("default_role", true));
        if (CollectionUtils.isEmpty(roleList)) {
            List<SysUserRole> sysUserRoleList = roleList.stream().map(sysRole -> {
                return new SysUserRole(sysUserExtend.getId(), sysRole.getId(), UseType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserRoleService.saveBatch(sysUserRoleList);
        }
    }

    @Override
    public Boolean modifyPass(String rawPassword, String newPassword) {
        Result result = authClients.pwdMatches(rawPassword);
        if (result.get("code").equals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)) {
            return false;
        }
        String userId = result.get("data").toString();
        result = authClients.generatePwd(newPassword);
        String enablePwd = result.get("data").toString();
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(enablePwd);
        return SqlHelper.retBool(sysUserMapper.updateById(sysUser));
    }

    @Override
    public List<SysUser> getPesetPassOfUserListByIds(List<String> ids) {
        Result result = authClients.getDefaultPwd();
        String defaultPwd = result.get("data").toString();
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }
        List<SysUser> collect = ids.stream().map(id -> {
            SysUser sysUser = sysUserMapper.selectById(id);
            sysUser.setPassword(defaultPwd);
            return sysUser;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Boolean retrievePass(SysUser sysUser) {
        Result result = authClients.generatePwd(sysUser.getPassword());
        String enablePwd = result.get("data").toString();
        if (StringUtils.isEmpty(enablePwd)) {
            return false;
        }
        sysUser.setPassword(enablePwd);
        return SqlHelper.retBool(sysUserMapper.updateById(sysUser));
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
    }

    @Override
    public SysUser findByPhone(String phone) {
        return sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("phone", phone));
    }

    @Override
    public SysUser findByEmail(String email) {
        return sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("email", email));
    }

    @Override
    public String getUserNameByWeChatCode(String code) {
        return "wangwu";
    }

    @Override
    public SecurityAuthUser getUserByUserName(String username) {
        SecurityAuthUser securityAuthUser;
        SysUser cacheUser = iCacheService.getUserBasisCache(username);
        if (Objects.nonNull(cacheUser)) {
            securityAuthUser = new SecurityAuthUser(cacheUser);
        } else {
            SysUser sysUser = this.findByUsername(username);
            if (Objects.isNull(sysUser)) {
                return null;
            }
            securityAuthUser = new SecurityAuthUser(sysUser);
            iCacheService.setUserBasisCache(username, sysUser);
        }
        Set<BaseSecurityMenu> cacheMenuPerm = iCacheService.getUserMenupermCache(username);
        if (Objects.nonNull(cacheMenuPerm)) {
            securityAuthUser.setMenuScopeList(cacheMenuPerm);
            return securityAuthUser;
        } else {
            //获取用户所拥有的用户组信息
            Set<String> groupIdSet = new HashSet<String>();
            Set<String> cacheGroupIdSet = iCacheService.getUserGroupCache(username);
            if (Objects.nonNull(cacheGroupIdSet)) {
                groupIdSet = cacheGroupIdSet;
            } else {
                //获取用户组(已激活的)
                List<SysUserGroup> sysUserGroupList = iSysUserGroupService.getSysUserGroupByUserId(securityAuthUser.getId());
                if (!CollectionUtils.isEmpty(sysUserGroupList)) {
                    groupIdSet = sysUserGroupList.stream().map(sysUserGroup -> sysUserGroup.getGroupId()).collect(Collectors.toSet());
                }
                iCacheService.setUserGroupCache(username, groupIdSet);
            }
            //获取用户所拥有的岗位信息
            Set<String> postIdSet = new HashSet<String>();
            Set<String> cachePostIdSet = iCacheService.getUserPostCache(username);
            if (Objects.nonNull(cachePostIdSet)) {
                postIdSet = cachePostIdSet;
            } else {
                //获取用户岗位(已激活的)
                List<SysScope> sysScopeList = iSysScopeService.getSysScopeByUserId(securityAuthUser.getId());
                if (!CollectionUtils.isEmpty(sysScopeList)) {
                    postIdSet = sysScopeList.stream().map(sysScope -> sysScope.getPostId()).collect(Collectors.toSet());
                }
                iCacheService.setUserPostCache(username, postIdSet);
            }
            //获取用户所拥有的角色信息
            Set<String> roleIdSet = new HashSet<String>();
            Set<String> cacheRoleIdSet = iCacheService.getUserRoleCache(username);
            if (Objects.nonNull(cacheRoleIdSet)) {
                roleIdSet = cacheRoleIdSet;
            } else {
                //获取用户角色(已激活的)
                List<SysUserRole> sysUserRoleList = iSysUserRoleService.getSysUserRoleByUserId(securityAuthUser.getId());
                if (!CollectionUtils.isEmpty(sysUserRoleList)) {
                    roleIdSet = sysUserRoleList.stream().map(sysUserRole -> sysUserRole.getId()).collect(Collectors.toSet());
                }
                iCacheService.setUserRoleCache(username, roleIdSet);
            }
            //获取用户菜单权限
            Set<BaseSecurityMenu> baseSecurityMenuSet = iSysMenuLimitService.getBaseSecurityMenuByScope(groupIdSet, postIdSet, roleIdSet);
            iCacheService.setUserMenupermCache(username, baseSecurityMenuSet);
            securityAuthUser.setMenuScopeList(baseSecurityMenuSet);
        }
        return securityAuthUser;
    }

    @Override
    public void saveUserExtendInfo(SysUserExtend sysUserExtend) {
        List<String> roleList = sysUserExtend.getRoleList();
        List<SysScope> scopeList = sysUserExtend.getSysScopeList();
        List<String> groupList = sysUserExtend.getGroupList();
        if (!CollectionUtils.isEmpty(roleList)) {
            List<SysUserRole> userRoleList = roleList.stream().map(roleId -> {
                return new SysUserRole(sysUserExtend.getId(), roleId, UseType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserRoleService.saveBatch(userRoleList);
        }
        if (!CollectionUtils.isEmpty(scopeList)) {
            scopeList.stream().forEach(scope -> {
                scope.setUserId(sysUserExtend.getId());
                scope.setDeptStatus(UseType.ENABLE.getStatus());
                scope.setPostStatus(UseType.ENABLE.getStatus());
            });
            iSysScopeService.saveBatch(scopeList);
        }
        if (!CollectionUtils.isEmpty(groupList)) {
            List<SysUserGroup> userGroupList = groupList.stream().map(groupId -> {
                return new SysUserGroup(sysUserExtend.getId(), groupId, UseType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserGroupService.saveBatch(userGroupList);
        }
    }

    @Override
    public void updateUserExtendInfo(SysUser sysUser, SysUserExtend sysUserExtend) {
        List<String> roles = sysUserExtend.getRoleList();
        List<SysScope> scopeList = sysUserExtend.getSysScopeList();
        List<String> groupIdList = sysUserExtend.getGroupList();
        Set<String> groupIdSet = new HashSet<>(groupIdList);
        Set<String> postIdSet = new HashSet<String>();
        Set<String> roleIdSet = new HashSet<String>();
        iSysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getId()));
        if (!CollectionUtils.isEmpty(roles)) {
            List<SysUserRole> userRoleList = roles.stream().map(roleId -> {
                roleIdSet.add(roleId);
                return new SysUserRole(sysUser.getId(), roleId, UseType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserRoleService.saveBatch(userRoleList);
        }
        iSysScopeService.remove(new QueryWrapper<SysScope>().eq("user_id", sysUser.getId()));
        if (!CollectionUtils.isEmpty(scopeList)) {
            scopeList.stream().forEach(scope -> {
                postIdSet.add(scope.getPostId());
                scope.setUserId(sysUser.getId());
                scope.setDeptStatus(UseType.ENABLE.getStatus());
                scope.setPostStatus(UseType.ENABLE.getStatus());
            });
            iSysScopeService.saveBatch(scopeList);
        }
        Set<BaseSecurityMenu> baseSecurityMenuSet = iSysMenuLimitService.getBaseSecurityMenuByScope(groupIdSet, postIdSet, roleIdSet);
        //更新用户缓存信息
        iCacheService.setUserExtendInfoCache(sysUser, groupIdSet, postIdSet, roleIdSet, baseSecurityMenuSet);
    }

}
