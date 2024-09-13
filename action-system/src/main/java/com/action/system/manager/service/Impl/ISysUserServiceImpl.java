package com.action.system.manager.service.Impl;

import com.action.call.vo.AuthUserInfoVo;
import com.action.common.biz.service.ICache;
import com.action.common.common.UserSetConstants;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.common.Result;
import com.action.common.core.constants.ActionConstants;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.common.security.util.SecurityUtil;
import com.action.system.bsup.service.*;
import com.action.system.bsup.struct.entity.SysScope;
import com.action.system.bsup.struct.entity.SysUserGroup;
import com.action.system.bsup.struct.entity.SysUserRole;
import com.action.system.manager.mapper.SysUserMapper;
import com.action.system.manager.service.ISysDataService;
import com.action.system.manager.service.ISysRoleService;
import com.action.system.manager.service.ISysUserService;
import com.action.system.manager.struct.entity.SysRole;
import com.action.system.manager.struct.entity.SysUser;
import com.action.system.manager.struct.vo.UserVo;
import com.action.system.sconf.service.ISysAppService;
import com.action.system.sconf.struct.entity.SysApp;
import com.action.system.manager.struct.converter.UserConverter;
import com.action.system.manager.struct.dto.SysUserDto;
import com.action.system.sconf.struct.vo.AppVo;
import com.action.system.manager.struct.vo.UserProfileVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
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
    private final ISysAppService iSysAppService;
    private final PasswordEncoder passwordEncoder;
    private final ICacheService iCacheService;
    private final ICache iCache;
    private final UserConverter userConverter;
    private final SecurityUtil securityUtil;

    @Override
    public UserVo getUserInfoById(String id) {
        UserVo userVo = this.getInfoById(id, UserVo.class);
        if (Objects.isNull(userVo)) {
            return null;
        }
        //获取用户所拥有的用户组信息
        Set<String> groupIdSet = iSysUserGroupService.getGroupIdSetAndCache(userVo.getId(), userVo.getUsername());
        //获取用户所拥有的岗位信息
        List<SysScope> sysScopeList = iSysScopeService.getSysScopeByUserId(userVo.getId());
        //获取用户所拥有的角色信息
        Set<String> roleIdSet = iSysUserRoleService.getRoleIdSetAndCache(userVo.getId(), userVo.getUsername());
        userVo.setGroupList(Objects.isNull(groupIdSet) ? List.of() : groupIdSet.stream().toList());
        userVo.setSysScopeList(Objects.isNull(sysScopeList) ? List.of() : sysScopeList);
        userVo.setRoleList(Objects.isNull(roleIdSet) ? List.of() : roleIdSet.stream().toList());
        return userVo;
    }

    @Override
    public boolean saveInfo(SysUserDto sysUserDto) {
        if (Objects.isNull(sysUserDto.getTenantId())) {
            sysUserDto.setTenantId(ActionConstants.topTenantId);
        }
        sysUserDto.setAvatar(sysUserDto.getAvatar() == null ? UserSetConstants.DEFAULT_AVATAR : sysUserDto.getAvatar());
        boolean isSave = this.save(sysUserDto);
        if (isSave) {
            this.saveUserExtendInfo(sysUserDto);
        }
        return isSave;
    }

    @Override
    public boolean updateInfo(SysUserDto sysUserDto) {
        boolean isUpdate = this.update(sysUserDto, this.getLambdaUpdateWrapper().eq(SysUser::getId, sysUserDto.getId())
                .eq(SysUser::getPhone, sysUserDto.getPhone())
                .eq(SysUser::getEmail, sysUserDto.getEmail()));
        if (isUpdate) {
            this.updateUserExtendInfo(sysUserDto);
        }
        return isUpdate;
    }

    @Override
    public boolean regist(SysUserDto sysUserDto) {
        String enablePwd = passwordEncoder.encode(sysUserDto.getPassword());
        if (StringUtils.isEmpty(enablePwd)) {
            return false;
        }
        if (Objects.isNull(sysUserDto.getTenantId())) {
            sysUserDto.setTenantId(ActionConstants.topTenantId);
        }
        sysUserDto.setPassword(enablePwd);
        sysUserDto.setAvatar(UserSetConstants.DEFAULT_AVATAR);
        boolean isSave = this.save(sysUserDto);
        if (isSave) {
            this.setUserDefaultRole(sysUserDto);
        }
        return isSave;
    }

    @Override
    public UserProfileVO getUserProfile() {
        String userId = securityUtil.getUserId();
        SysUser sysUser = this.getById(userId);
        return userConverter.sysUserToUserProfileVO(sysUser);
    }

    @Override
    public boolean editInfo(SysUser sysUser) {
        SysUser u = sysUserMapper.selectById(sysUser.getId());
        if (Objects.isNull(u)) {
            return false;
        }
        sysUser.setUsername(u.getUsername());//用户名不允许修改
        sysUser.setPassword(u.getPassword());//密码不允许修改
        return this.updateById(u);
    }

    @Override
    public boolean disable(String id) {
        return this.update(this.getLambdaUpdateWrapper().set(SysUser::getStatus, StatusType.DISABLED.getStatus()).eq(SysUser::getId, id));
    }

    @Override
    public boolean enable(String id) {
        return this.update(this.getLambdaUpdateWrapper().set(SysUser::getStatus, StatusType.ENABLE.getStatus()).eq(SysUser::getId, id));
    }

    @Override
    public void setUserDefaultRole(SysUserDto sysUserDto) {
        List<SysRole> roleList = iSysRoleService.listObjs(this.getLambdaQueryWrapper(new SysRole()).eq(SysRole::getDefaultRole, true));
        if (CollectionUtils.isEmpty(roleList)) {
            List<SysUserRole> sysUserRoleList = roleList.stream().map(sysRole -> {
                return new SysUserRole(sysUserDto.getId(), sysRole.getId(), StatusType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserRoleService.saveBatch(sysUserRoleList);
        }
    }

    @Override
    public Boolean modifyPass(String rawPassword, String newPassword) {
        boolean matches = passwordEncoder.matches(rawPassword, securityUtil.getPassword());
        String enablePwd = passwordEncoder.encode(newPassword);
        String userId = securityUtil.getUserId();
        return this.update(this.getLambdaUpdateWrapper().set(matches, SysUser::getPassword, enablePwd).eq(SysUser::getId, userId));
    }

    @Override
    public Boolean resetPassBatchByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            return false;
        }
        String defaultPwd = passwordEncoder.encode(UserSetConstants.DEFAULT_PASSWORD);
        return this.update(this.getLambdaUpdateWrapper().set(SysUser::getPassword, defaultPwd).in(SysUser::getId, ids));
    }

    @Override
    public Boolean retrievePass(SysUser sysUser) {
        String enablePwd = passwordEncoder.encode(sysUser.getPassword());
        if (StringUtils.isEmpty(enablePwd)) {
            return false;
        }
        sysUser.setPassword(enablePwd);
        return this.updateById(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectOne(this.getLambdaQueryWrapper().eq(SysUser::getUsername, username));
    }

    @Override
    public AuthUserInfoVo findByPhone(String phone) {
        SysUser sysUser = sysUserMapper.selectOne(this.getLambdaQueryWrapper().eq(SysUser::getPhone, phone));
        return Objects.nonNull(sysUser) ? getUserByUserName(sysUser.getUsername()) : new AuthUserInfoVo();
    }

    @Override
    public AuthUserInfoVo findByOpenId(String openid) {
        SysUser sysUser = sysUserMapper.selectOne(this.getLambdaQueryWrapper().eq(SysUser::getOpenid, openid));
        return Objects.nonNull(sysUser) ? getUserByUserName(sysUser.getUsername()) : new AuthUserInfoVo();
    }

    @Override
    public AuthUserInfoVo findByEmail(String email) {
        SysUser sysUser = sysUserMapper.selectOne(this.getLambdaQueryWrapper().eq(SysUser::getEmail, email));
        return Objects.nonNull(sysUser) ? getUserByUserName(sysUser.getUsername()) : new AuthUserInfoVo();
    }

    @Override
    public AuthUserInfoVo findByAppId(String appid) {
        AppVo appVo = iSysAppService.selectOneBy((e) -> e.getOne(this.getLambdaQueryWrapper(new SysApp()).eq(SysApp::getAppId, appid).eq(SysApp::getStatus, StatusType.ENABLE.getStatus())), AppVo.class);
        AtomicReference<AuthUserInfoVo> authuserInfoVo = new AtomicReference<>(new AuthUserInfoVo());
        Optional.ofNullable(appVo).ifPresent(app -> {
            AuthUserInfoVo au = getUserByUserName(app.getUsername());
            if (au != null) {
                au.setOpen(true);
                authuserInfoVo.set(au);
            }
        });
        return authuserInfoVo.get();
    }

    @Override
    public String getUserNameByWeChatCode(String code) {
        return "";
    }

    @Override
    public AuthUserInfoVo getUserByUserName(String username) {
        AuthUserInfoVo authUserInfoVo = getAuthUserInfoVoAndCache(username);
        if (authUserInfoVo == null) return null;
        Set<String> groupIdSet = new HashSet<String>();
        Set<String> postIdSet = new HashSet<String>();
        Set<String> roleIdSet = new HashSet<String>();
        List<BaseSecurityMenu> cacheMenuPerm = iCacheService.getUserMenupermCache(username);
        if (Objects.nonNull(cacheMenuPerm)) {
            authUserInfoVo.setMenuScopeList(cacheMenuPerm);
            return authUserInfoVo;
        } else {
            //获取用户所拥有的用户组信息
            groupIdSet = iSysUserGroupService.getGroupIdSetAndCache(authUserInfoVo.getId(), username);
            //获取用户所拥有的岗位信息
            postIdSet = iSysScopeService.getPostIdSetAndCache(authUserInfoVo.getId(), username);
            //获取用户所拥有的角色信息
            roleIdSet = iSysUserRoleService.getRoleIdSetAndCache(authUserInfoVo.getId(), username);
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

    private AuthUserInfoVo getAuthUserInfoVoAndCache(String username) {
        SysUser sysUser = iCacheService.getUserBasisCache(username);
        if (Objects.isNull(sysUser)) {
            sysUser = this.findByUsername(username);
            if (Objects.isNull(sysUser)) {
                return null;
            }
            iCacheService.setUserBasisCache(username, sysUser);
        }
        return userConverter.sysUserToAuthUserVo(sysUser);
    }

    //todo 添加事务
    @Override
    public void saveUserExtendInfo(SysUserDto sysUserDto) {
        iSysUserRoleService.saveBatchByUserId(sysUserDto.getId(), sysUserDto.getRoleList());
        iSysScopeService.saveBatchByUserId(sysUserDto.getId(), sysUserDto.getSysScopeList());
        iSysUserGroupService.saveBatchByUserId(sysUserDto.getId(), sysUserDto.getGroupList());
    }

    //todo 添加事务
    @Override
    public void updateUserExtendInfo(SysUserDto sysUserDto) {
        List<String> groupIdList = sysUserDto.getGroupList();
        List<String> roleList = sysUserDto.getRoleList();
        Set<String> groupIdSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(groupIdList)) {
            groupIdSet = new HashSet<>(groupIdList);
        }
        Set<String> roleIdSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(roleList)) {
            roleIdSet = new HashSet<>(roleList);
        }
        iSysUserRoleService.updateBatchByUserId(sysUserDto.getId(), roleList);//更新用户角色
        Set<String> postIdSet = iSysScopeService.updateBatchByUserId(sysUserDto.getId(), sysUserDto.getSysScopeList());//更新用户岗位
        iSysUserGroupService.updateBatchByUserId(sysUserDto.getId(), sysUserDto.getGroupList());//更新用户组
        List<BaseSecurityMenu> baseSecurityMenuSet = iSysMenuLimitService.getBaseSecurityMenuByScope(groupIdSet, postIdSet, roleIdSet);
        //更新用户缓存信息
        iCacheService.setUserExtendInfoCache(userConverter.sysUserDtoToSysUser(sysUserDto), groupIdSet, postIdSet, roleIdSet, baseSecurityMenuSet);
    }

}
