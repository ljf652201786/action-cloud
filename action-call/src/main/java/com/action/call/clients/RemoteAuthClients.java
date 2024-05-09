package com.action.call.clients;

import com.action.call.factory.RemoteAuthClientsFallbackFactory;
import com.action.common.core.common.Result;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "authClients", value = ClientsCirectoryTable.AUTH_SERVICE, fallbackFactory = RemoteAuthClientsFallbackFactory.class)
public interface RemoteAuthClients {

    @LoadBalanced  //启用负载均衡
    @RequestMapping(value = "auth/extend/generatePwd", method = RequestMethod.GET)
    Result generatePwd(@RequestParam("password") String password);

    @LoadBalanced
    @RequestMapping(value = "auth/extend/getDefaultPwd", method = RequestMethod.GET)
    Result getDefaultPwd();

    @LoadBalanced
    @RequestMapping(value = "auth/extend/getCurrentUser", method = RequestMethod.GET)
    Result getCurrentUser();

    @LoadBalanced
    @RequestMapping(value = "auth/extend/getCurrentUserName", method = RequestMethod.GET)
    Result getCurrentUserName();

    @LoadBalanced
    @RequestMapping(value = "auth/extend/pwdMatches", method = RequestMethod.GET)
    Result pwdMatches(@RequestParam("rawPassword") String rawPassword);
}
