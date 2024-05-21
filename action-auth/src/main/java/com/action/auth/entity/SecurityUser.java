package com.action.auth.entity;

/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.base.BaseSecurityUser;
import com.action.common.core.constants.StringPool;
import com.action.common.core.enums.UserStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/03/25
 */
public class SecurityUser extends BaseSecurityUser implements UserDetails, CredentialsContainer {

    public SecurityUser() {
    }

    public <T extends BaseSecurityUser> SecurityUser(List<? extends BaseSecurityMenu> menuScopeList, String userId, String username, String password, String status) {
        super(menuScopeList, userId, username, password, status);
    }

    public <T extends BaseSecurityUser> SecurityUser(T user) {
        super(user.getMenuScopeList(), user.getId(), user.getUsername(), user.getPassword(), user.getStatus());
    }

    public static String buildPer(BaseSecurityMenu menu) {
        if (Objects.nonNull(menu) && StringUtils.isNotEmpty(menu.getPath()) && StringUtils.isNotEmpty(menu.getPerms())) {
            return menu.getPath() + StringPool.LEFT_SQ_BRACKET + menu.getPerms() + StringPool.RIGHT_SQ_BRACKET;
        }
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = Collections.EMPTY_LIST;
        List<? extends BaseSecurityMenu> menuScopeList = this.getMenuScopeList();
        // 请求权限
        if (!CollectionUtils.isEmpty(menuScopeList)) {
            List<SimpleGrantedAuthority> menuCollect = menuScopeList.stream()
                    .filter(menu -> StringUtils.isNotEmpty(menu.getPath()) && StringUtils.isNotEmpty(menu.getPerms()))
                    .map(menu -> {
                        return new SimpleGrantedAuthority(buildPer(menu));
                    }).collect(Collectors.toList());
            authorityList.addAll(menuCollect);
        }

        // 添加角色
        /*Set<? extends BaseSecurityRole> roleScopeList = this.getRoleScopeList();
        if (!Collections.isEmpty(roleScopeList)) {
            List<SimpleGrantedAuthority> roleCollect = roleScopeList.stream().filter(role -> StringUtils.isNotEmpty(role.getCode())).map(role -> {
                return new SimpleGrantedAuthority(role.getCode());
            }).collect(Collectors.toList());
            authorityList.addAll(roleCollect);
        }*/
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus().equals(UserStatusEnum.ENABLE.getStatus());
    }

    @Override
    public void eraseCredentials() {
        this.setPassword(null);
    }
}
