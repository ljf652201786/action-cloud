package com.action.auth.controller;

import com.action.auth.struct.entity.Oauth2RegisteredClient;
import com.action.auth.service.IOauth2RegisteredClientService;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseMpController;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 客户端管理接口
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/17
 */
@RestController("client")
@RequiredArgsConstructor
public class ClientManagerController implements BaseMpController<IOauth2RegisteredClientService, Oauth2RegisteredClient> {

    private final IOauth2RegisteredClientService iOauth2RegisteredClientService;

    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(Oauth2RegisteredClient oauth2RegisteredClient, BaseQuery query) {
        return this.page(iOauth2RegisteredClientService, oauth2RegisteredClient, query);
    }

    @RequestMapping(value = "registered", method = RequestMethod.POST)
    public Result registered(@RequestBody Oauth2RegisteredClient oauth2RegisteredClient) {
        boolean isRegistered = iOauth2RegisteredClientService.registered(oauth2RegisteredClient);
        return Result.judge(isRegistered, "注册客户端成功", "注册客户端失败");
    }
}
