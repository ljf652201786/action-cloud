package com.action.auth.service;

import com.action.auth.entity.SecurityUser;
import com.action.call.clients.RemoteSystemClients;
import com.action.call.vo.AuthUserInfoVo;
import com.action.common.core.common.Result;
import com.action.common.core.common.ResultCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Description: 第三方认证用户认证服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/22
 */
@Service
@RequiredArgsConstructor
public class ThirdDetailsService {

    private final RemoteSystemClients remoteSystemClients;

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
    public UserDetails loadUserByOpenid(String openid) {
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

}
