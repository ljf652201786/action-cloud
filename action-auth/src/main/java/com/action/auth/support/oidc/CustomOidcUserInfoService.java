package com.action.auth.support.oidc;

import com.action.call.clients.RemoteSystemClients;
import com.action.common.core.common.Result;
import com.action.common.core.common.ResultCode;
import com.action.common.entity.SecurityAuthUser;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 自定义 OIDC 用户信息服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOidcUserInfoService {

    private final RemoteSystemClients remoteSystemClients;

    public CustomOidcUserInfo loadUserByUsername(String username) {
        try {
            Result result = remoteSystemClients.getUserByUserName(username);
            if (!result.get("code").equals(HttpServletResponse.SC_OK)) {
                throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
            }
            SecurityAuthUser securityAuthUser = new SecurityAuthUser(result.get("data"));
            return new CustomOidcUserInfo(createUser(securityAuthUser));
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return null;
        }
    }

    private Map<String, Object> createUser(SecurityAuthUser securityAuthUser) {
        return CustomOidcUserInfo.customBuilder()
                .username(securityAuthUser.getUsername())
                .nickname(securityAuthUser.getNickName())
                .status(securityAuthUser.getStatus())
                .email(securityAuthUser.getEmail())
                .profile(securityAuthUser.getAvatar())
                .build()
                .getClaims();
    }

    /*private UserAuthInfo getUserAuthInfo(String username) {
        UserAuthInfo userAuthInfo = new UserAuthInfo();
        userAuthInfo.setUsername("zhangsan");
        userAuthInfo.setNickname("张三");
        userAuthInfo.setMobile("13860321661");
        userAuthInfo.setEmail("6565@qq.com");
        return userAuthInfo;
    }*/

}
