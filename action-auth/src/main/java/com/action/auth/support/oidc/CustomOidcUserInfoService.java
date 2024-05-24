package com.action.auth.support.oidc;

import com.action.call.clients.RemoteSystemClients;
import com.action.call.vo.AuthUserInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
            AuthUserInfoVo authUserInfoVo = remoteSystemClients.getUserByUserName(username);

            Assert.isTrue(authUserInfoVo != null, "用户不存在");

            return new CustomOidcUserInfo(createUser(authUserInfoVo));
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return null;
        }
    }

    private Map<String, Object> createUser(AuthUserInfoVo authUserInfoVo) {
        return CustomOidcUserInfo.customBuilder()
                .username(authUserInfoVo.getUsername())
                .nickname(authUserInfoVo.getNickName())
                .status(authUserInfoVo.getStatus())
                .email(authUserInfoVo.getEmail())
                .profile(authUserInfoVo.getAvatar())
                .build()
                .getClaims();
    }

}
