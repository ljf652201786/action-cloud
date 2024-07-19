package com.action.auth.service;

import com.action.auth.entity.SecurityUser;
import com.action.call.clients.RemoteSystemClients;
import com.action.call.vo.AuthUserInfoVo;
import com.action.common.enums.StatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Description: 系统用户信息加载实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/20
 */
@Service
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService {

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
}
