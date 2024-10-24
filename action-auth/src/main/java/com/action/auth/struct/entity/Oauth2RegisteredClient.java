package com.action.auth.struct.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 注册客户端
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("oauth2_registered_client")
public class Oauth2RegisteredClient {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @TableField("client_id")
    private String clientId;
    @TableField("client_id_issued_at")
    private String clientIdIssuedAt;
    @TableField("client_secret")
    private String clientSecret;
    @TableField("client_secret_expires_at")
    private String clientSecretExpiresAt;
    @TableField("client_name")
    private String clientName;
    @TableField("client_authentication_methods")
    private String clientAuthenticationMethods;
    @TableField("authorization_grant_types")
    private String authorizationGrantTypes;
    @TableField("redirect_uris")
    private String redirectUris;
    @TableField("post_logout_redirect_uris")
    private String postLogoutRedirectUris;
    @TableField("scopes")
    private String scopes;
    @TableField("client_settings")
    private String clientSettings;
    @TableField("token_settings")
    private String tokenSettings;
}
