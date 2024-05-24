package com.action.call.clients;

import com.action.call.config.FeignDecoderConfig;
import com.action.call.factory.RemoteAuthClientsFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "authClients", value = ClientsCirectoryTable.AUTH_SERVICE, fallbackFactory = RemoteAuthClientsFallbackFactory.class, configuration = {FeignDecoderConfig.class})
public interface RemoteAuthClients {


}
