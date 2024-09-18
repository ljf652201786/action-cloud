package com.action.auth.service;

import com.action.call.clients.RemoteSystemClients;
import com.action.call.struct.vo.AuthUserInfoVo;
import com.action.common.enums.StatusType;
import com.action.common.oauth2.entity.SecurityUser;
import com.action.common.oauth2.service.IThirdDetailsService;
import com.action.common.oauth2.support.oidc.CustomOidcUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Description: 系统用户信息加载实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/20
 */
@Service
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService, IThirdDetailsService {

    private final RemoteSystemClients remoteSystemClients;

    /**
     * 根据用户名获取用户信息(用户名、密码和角色权限)
     * <p>
     * 用户名、密码用于后续认证，认证成功之后将权限授予用户
     *
     * @param username 用户名
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        AuthUserInfoVo authUserInfoVo = remoteSystemClients.getUserByUserName(username);
        Assert.isTrue(authUserInfoVo != null, "用户不存在");
        if (!StatusType.ENABLE.getStatus().equals(authUserInfoVo.getStatus())) {
            throw new DisabledException("该账户已被禁用!");
        }
        return new SecurityUser(authUserInfoVo);
    }


    /**
     * 手机号码认证方式
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    public UserDetails getUserByPhone(String mobile) {
        AuthUserInfoVo authUserInfoVo = remoteSystemClients.getUserByPhone(mobile);
        Assert.isTrue(authUserInfoVo != null, "用户不存在");
        UserDetails userDetails = new SecurityUser(authUserInfoVo);
        if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param openid 微信公众平台唯一身份标识
     * @return {@link UserDetails}
     */
    public UserDetails getUserByOpenid(String openid) {
        // 根据 openid 获取微信用户认证信息
        AuthUserInfoVo authUserInfoVo = remoteSystemClients.getUserByOpenId(openid);
        Assert.isTrue(authUserInfoVo != null, "用户不存在");
        //todo 用户不存在，注册成为新用户(待实现)
        UserDetails userDetails = new SecurityUser(authUserInfoVo);
        if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }

    public UserDetails getUserByAppId(String appid) {
        // 根据 openid 获取微信用户认证信息
        AuthUserInfoVo authUserInfoVo = remoteSystemClients.getUserByAppId(appid);
        Assert.isTrue(authUserInfoVo != null, "用户不存在");
        //todo 用户不存在，注册成为新用户(待实现)
        UserDetails userDetails = new SecurityUser(authUserInfoVo);
        if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }

    public CustomOidcUserInfo getOidcUserByUsername(String username) {
        try {
            AuthUserInfoVo authUserInfoVo = remoteSystemClients.getUserByUserName(username);
            Assert.isTrue(authUserInfoVo != null, "用户不存在");
            return new CustomOidcUserInfo(createUser(authUserInfoVo));
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, Object> createUser(AuthUserInfoVo authUserInfoVo) {
        return CustomOidcUserInfo.customBuilder()
                .username(authUserInfoVo.getUsername())
                .nickname(new String(authUserInfoVo.getNickname(), StandardCharsets.UTF_8))
                .status(authUserInfoVo.getStatus())
                .email(authUserInfoVo.getEmail())
                .profile(authUserInfoVo.getAvatar())
                .build()
                .getClaims();
    }
}
