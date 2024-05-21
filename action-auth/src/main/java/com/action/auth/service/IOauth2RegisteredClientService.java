package com.action.auth.service;

import com.action.auth.entity.Oauth2RegisteredClient;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IOauth2RegisteredClientService extends IService<Oauth2RegisteredClient> {

    boolean registered(Oauth2RegisteredClient oauth2RegisteredClient);
}
