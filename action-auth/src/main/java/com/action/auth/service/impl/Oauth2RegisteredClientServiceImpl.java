package com.action.auth.service.impl;

import com.action.auth.entity.Oauth2RegisteredClient;
import com.action.auth.mapper.Oauth2RegisteredClientMapper;
import com.action.auth.service.IOauth2RegisteredClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
public class Oauth2RegisteredClientServiceImpl extends ServiceImpl<Oauth2RegisteredClientMapper, Oauth2RegisteredClient> implements IOauth2RegisteredClientService {
    @Resource
    private JdbcRegisteredClientRepository registeredClientRepository;

    @Override
    public boolean registered(Oauth2RegisteredClient oauth2RegisteredClient) {
        if (StringUtils.isEmpty(oauth2RegisteredClient.getClientId()) || StringUtils.isEmpty(oauth2RegisteredClient.getClientSecret())) {
            return false;
        }
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(oauth2RegisteredClient.getClientId());
        String id = registeredClient != null ? registeredClient.getId() : UUID.randomUUID().toString();
        RegisteredClient appClient = RegisteredClient.withId(id)
                .clientId(oauth2RegisteredClient.getClientId())
                .clientSecret(oauth2RegisteredClient.getClientSecret())
                .clientName(StringUtils.isNoneEmpty(oauth2RegisteredClient.getClientName()) ? oauth2RegisteredClient.getClientName() : "客户端")

                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD) // 密码模式
                .redirectUri("http://127.0.0.1:8080/authorized")
                .postLogoutRedirectUri("http://127.0.0.1:8080/logged-out")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofDays(1)).build())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        registeredClientRepository.save(appClient);
        return true;
    }
}
