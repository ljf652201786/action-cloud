package com.action.auth.service;

import com.action.auth.entity.SecurityUser;
import com.action.call.clients.RemoteSystemClients;
import com.action.common.core.common.Result;
import com.action.common.core.common.ResultCode;
import com.action.common.entity.SecurityAuthUser;
import com.action.common.enums.UseType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        Result result = remoteSystemClients.getUserByUserName(username);
        if (!result.get("code").equals(HttpServletResponse.SC_OK)) {
            throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
        }
        SecurityAuthUser securityAuthUser = new SecurityAuthUser(result.get("data"));
        if (!UseType.ENABLE.getStatus().equals(securityAuthUser.getStatus())) {
            throw new DisabledException("该账户已被禁用!");
        }

        return new SecurityUser(securityAuthUser);
    }
}
