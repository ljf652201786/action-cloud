/*
package com.action.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

*/
/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/03/27
 *//*

public class WeChatAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 570L;
    private Object principal;
    private Object credentials;
    private String username;

    public WeChatAuthenticationToken(String username) {
        super(null);
        this.username = username;
        this.setAuthenticated(false);
    }

    public WeChatAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public String getUsername() {
        return username;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

}


*/
