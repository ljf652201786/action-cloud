package com.action.auth.entity;/*
package com.action.auth.server.entity;

import com.action.common.entity.SecurityAuthUser;
import com.action.common.enums.UseType;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Data
public class ThirdDetails implements UserDetails {

    */
/**
     * 用户ID
     *//*

    private String id;

    */
/**
     * 用户名
     *//*

    private String username;

    */
/**
     * 用户状态
     *//*

    private Boolean enabled;

    */
/**
     * 扩展字段：认证身份标识，枚举值如下：
     *//*

    private String authenticationIdentity;

    */
/**
     * 用户信息构造
     *
     * @param securityAuthUser 认证信息
     *//*

    public ThirdDetails(SecurityAuthUser securityAuthUser) {
        this.setId(securityAuthUser.getId());
        this.setUsername(securityAuthUser.getUsername());
        this.setEnabled(UseType.ENABLE.getStatus().equals(securityAuthUser.getStatus()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_SET;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.enabled;
    }
}
*/
