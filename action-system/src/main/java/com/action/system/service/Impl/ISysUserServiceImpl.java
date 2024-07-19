package com.action.system.service.Impl;

import com.action.call.vo.AuthUserInfoVo;
import com.action.common.biz.service.ICache;
import com.action.common.common.UserSetConstants;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.common.security.util.SecurityUtil;
import com.action.system.struct.converter.UserConverter;
import com.action.system.struct.dto.SysUserExtend;
import com.action.system.mapper.*;
import com.action.system.service.*;
import com.action.system.struct.entity.*;
import com.action.system.struct.vo.UserProfileVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 用户服务实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private final SysUserMapper sysUserMapper;
    private final ISysMenuLimitService iSysMenuLimitService;
    private final ISysScopeService iSysScopeService;
    private final ISysRoleService iSysRoleService;
    private final ISysUserRoleService iSysUserRoleService;
    private final ISysUserGroupService iSysUserGroupService;
    private final ISysDataService iSysDataService;
    private final PasswordEncoder passwordEncoder;
    private final ICacheService iCacheService;
    private final ICache iCache;
    private final UserConverter userConverter;
    private final SecurityUtil securityUtil;

    @Override
    public Boolean regist(SysUserExtend sysUser) {
        String enablePwd = passwordEncoder.encode(sysUser.getPassword());
        if (StringUtils.isEmpty(enablePwd)) {
            return false;
        }
        sysUser.setPassword(enablePwd);
        sysUser.setAvatar(UserSetConstants.DEFAULT_AVATAR);
        boolean isSave = SqlHelper.retBool(sysUserMapper.insert(sysUser));
        return isSave;
    }

    @Override
    public UserProfileVO getUserProfile() {
        String userId = securityUtil.getUserId();
        SysUser sysUser = this.getById(userId);
        return userConverter.sysUserToUserProfileVO(sysUser);
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
        List<SysRole> roleList = iSysRoleService.listObjs(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getDefaultRole, true));
        if (CollectionUtils.isEmpty(roleList)) {
            List<SysUserRole> sysUserRoleList = roleList.stream().map(sysRole -> {
                return new SysUserRole(sysUserExtend.getId(), sysRole.getId(), StatusType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserRoleService.saveBatch(sysUserRoleList);
        }
    }

    @Override
    public Boolean modifyPass(String rawPassword, String newPassword) {
        boolean matches = passwordEncoder.matches(rawPassword, securityUtil.getPassword());
        String enablePwd = passwordEncoder.encode(newPassword);
        String userId = securityUtil.getUserId();
        return SqlHelper.retBool(sysUserMapper.update(Wrappers.<SysUser>lambdaUpdate().set(matches, SysUser::getPassword, enablePwd).eq(SysUser::getId, userId)));
    }

    @Override
    public Boolean resetPassBatchByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            return false;
        }
        String defaultPwd = passwordEncoder.encode(UserSetConstants.DEFAULT_PASSWORD);
        return SqlHelper.retBool(sysUserMapper.update(Wrappers.<SysUser>lambdaUpdate().set(SysUser::getPassword, defaultPwd).in(SysUser::getId, ids)));
    }

    @Override
    public Boolean retrievePass(SysUser sysUser) {
        String enablePwd = passwordEncoder.encode(sysUser.getPassword());
        if (StringUtils.isEmpty(enablePwd)) {
            return false;
        }
        sysUser.setPassword(enablePwd);
        return SqlHelper.retBool(sysUserMapper.updateById(sysUser));
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
    }

    @Override
    public AuthUserInfoVo findByPhone(String phone) {
        SysUser sysUser = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getPhone, phone));
        return userConverter.sysUserToAuthUserVo(sysUser);
    }

    @Override
    public AuthUserInfoVo findByEmail(String email) {
        SysUser sysUser = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getEmail, email));
        return userConverter.sysUserToAuthUserVo(sysUser);
    }

    @Override
    public String getUserNameByWeChatCode(String code) {
        return "wangwu";
    }

    @Override
    public AuthUserInfoVo getUserByUserName(String username) {
        AuthUserInfoVo authUserInfoVo;
        SysUser sysUser = iCacheService.getUserBasisCache(username);
        if (Objects.isNull(sysUser)) {
            sysUser = this.findByUsername(username);
            if (Objects.isNull(sysUser)) {
                return null;
            }
            iCacheService.setUserBasisCache(username, sysUser);
        }
        authUserInfoVo = userConverter.sysUserToAuthUserVo(sysUser);

        Set<String> groupIdSet = new HashSet<String>();
        Set<String> postIdSet = new HashSet<String>();
        Set<String> roleIdSet = new HashSet<String>();
        List<BaseSecurityMenu> cacheMenuPerm = iCacheService.getUserMenupermCache(username);
        if (Objects.nonNull(cacheMenuPerm)) {
            authUserInfoVo.setMenuScopeList(cacheMenuPerm);
            return authUserInfoVo;
        } else {
            //获取用户所拥有的用户组信息
            Set<String> cacheGroupIdSet = iCacheService.getUserGroupCache(username);
            if (Objects.nonNull(cacheGroupIdSet)) {
                groupIdSet = cacheGroupIdSet;
            } else {
                //获取用户组(已激活的)
                List<SysUserGroup> sysUserGroupList = iSysUserGroupService.getSysUserGroupByUserId(authUserInfoVo.getId());
                if (!CollectionUtils.isEmpty(sysUserGroupList)) {
                    groupIdSet = sysUserGroupList.stream().map(sysUserGroup -> sysUserGroup.getGroupId()).collect(Collectors.toSet());
                }
                iCacheService.setUserGroupCache(username, groupIdSet);
            }
            //获取用户所拥有的岗位信息
            Set<String> cachePostIdSet = iCacheService.getUserPostCache(username);
            if (Objects.nonNull(cachePostIdSet)) {
                postIdSet = cachePostIdSet;
            } else {
                //获取用户岗位(已激活的)
                List<SysScope> sysScopeList = iSysScopeService.getSysScopeByUserId(authUserInfoVo.getId());
                if (!CollectionUtils.isEmpty(sysScopeList)) {
                    postIdSet = sysScopeList.stream().map(sysScope -> sysScope.getPostId()).collect(Collectors.toSet());
                }
                iCacheService.setUserPostCache(username, postIdSet);
            }
            //获取用户所拥有的角色信息
            Set<String> cacheRoleIdSet = iCacheService.getUserRoleCache(username);
            if (Objects.nonNull(cacheRoleIdSet)) {
                roleIdSet = cacheRoleIdSet;
            } else {
                //获取用户角色(已激活的)
                List<SysUserRole> sysUserRoleList = iSysUserRoleService.getSysUserRoleByUserId(authUserInfoVo.getId());
                if (!CollectionUtils.isEmpty(sysUserRoleList)) {
                    roleIdSet = sysUserRoleList.stream().map(sysUserRole -> sysUserRole.getId()).collect(Collectors.toSet());
                }
                iCacheService.setUserRoleCache(username, roleIdSet);
            }
            //获取用户菜单权限
            List<BaseSecurityMenu> baseSecurityMenuSet = iSysMenuLimitService.getBaseSecurityMenuByScope(groupIdSet, postIdSet, roleIdSet);
            authUserInfoVo.setMenuScopeList(baseSecurityMenuSet);
            iCacheService.setUserMenupermCache(username, baseSecurityMenuSet);
        }
        //异步获取用户数据权限
        Set<DataRowFilterStruct> userDataPermRowCache = iCache.getUserDataPermRowCache(username);
        if (Objects.isNull(userDataPermRowCache)) {
            userDataPermRowCache = iSysDataService.getUserDataRowPerm(groupIdSet, postIdSet, roleIdSet);
            iCacheService.setUserDataPermRowCache(username, userDataPermRowCache);
        }
        Map<String, Set<String>> userDataPermColumnCache = iCache.getUserDataPermColumnCache(username);
        if (Objects.isNull(userDataPermColumnCache)) {
            userDataPermColumnCache = iSysDataService.getUserDataColumnPerm(groupIdSet, postIdSet, roleIdSet);
            iCacheService.setUserDataPermColumnCache(username, userDataPermColumnCache);
        }
        return authUserInfoVo;
    }

    @Override
    public void saveUserExtendInfo(SysUserExtend sysUserExtend) {
        List<String> roleList = sysUserExtend.getRoleList();
        List<SysScope> scopeList = sysUserExtend.getSysScopeList();
        List<String> groupList = sysUserExtend.getGroupList();
        if (!CollectionUtils.isEmpty(roleList)) {
            List<SysUserRole> userRoleList = roleList.stream().map(roleId -> {
                return new SysUserRole(sysUserExtend.getId(), roleId, StatusType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserRoleService.saveBatch(userRoleList);
        }
        if (!CollectionUtils.isEmpty(scopeList)) {
            scopeList.stream().forEach(scope -> {
                scope.setUserId(sysUserExtend.getId());
                scope.setDeptStatus(StatusType.ENABLE.getStatus());
                scope.setPostStatus(StatusType.ENABLE.getStatus());
            });
            iSysScopeService.saveBatch(scopeList);
        }
        if (!CollectionUtils.isEmpty(groupList)) {
            List<SysUserGroup> userGroupList = groupList.stream().map(groupId -> {
                return new SysUserGroup(sysUserExtend.getId(), groupId, StatusType.ENABLE.getStatus());
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
        iSysUserRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, sysUser.getId()));
        if (!CollectionUtils.isEmpty(roles)) {
            List<SysUserRole> userRoleList = roles.stream().map(roleId -> {
                roleIdSet.add(roleId);
                return new SysUserRole(sysUser.getId(), roleId, StatusType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserRoleService.saveBatch(userRoleList);
        }
        iSysScopeService.remove(Wrappers.<SysScope>lambdaQuery().eq(SysScope::getUserId, sysUser.getId()));
        if (!CollectionUtils.isEmpty(scopeList)) {
            scopeList.stream().forEach(scope -> {
                postIdSet.add(scope.getPostId());
                scope.setUserId(sysUser.getId());
                scope.setDeptStatus(StatusType.ENABLE.getStatus());
                scope.setPostStatus(StatusType.ENABLE.getStatus());
            });
            iSysScopeService.saveBatch(scopeList);
        }
        List<BaseSecurityMenu> baseSecurityMenuSet = iSysMenuLimitService.getBaseSecurityMenuByScope(groupIdSet, postIdSet, roleIdSet);
        //更新用户缓存信息
        iCacheService.setUserExtendInfoCache(sysUser, groupIdSet, postIdSet, roleIdSet, baseSecurityMenuSet);
    }

}
