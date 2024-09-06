package com.action.auth.service;

import com.action.auth.struct.entity.Oauth2RegisteredClient;
import com.action.common.mybatisplus.extend.base.BaseMpService;

public interface IOauth2RegisteredClientService extends BaseMpService<Oauth2RegisteredClient> {

    boolean registered(Oauth2RegisteredClient oauth2RegisteredClient);
}
