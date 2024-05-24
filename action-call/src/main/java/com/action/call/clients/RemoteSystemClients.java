package com.action.call.clients;

import com.action.call.config.FeignDecoderConfig;
import com.action.call.factory.RemoteSystemClientsFallbackFactory;
import com.action.call.vo.AuthUserInfoVo;
import com.action.call.vo.LogLoginVo;
import com.action.call.vo.LogRequestVo;
import com.action.call.vo.LogSMSVo;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.common.Result;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@FeignClient(contextId = "systemClients", value = ClientsCirectoryTable.SYSTEM_SERVICE, fallbackFactory = RemoteSystemClientsFallbackFactory.class, configuration = {FeignDecoderConfig.class})
public interface RemoteSystemClients {

    @LoadBalanced  //启用负载均衡
    @RequestMapping(value = "user/getUserByPhone", method = RequestMethod.GET)
    AuthUserInfoVo getUserByPhone(@RequestParam("phone") String phone);

    @LoadBalanced  //启用负载均衡
    @RequestMapping(value = "user/getUserByOpenId", method = RequestMethod.GET)
    AuthUserInfoVo getUserByOpenId(@RequestParam("openid") String openid);

    @LoadBalanced
    @RequestMapping(value = "user/getUserByEmail", method = RequestMethod.GET)
    AuthUserInfoVo getUserByEmail(@RequestParam("email") String email);

    @LoadBalanced
    @RequestMapping(value = "user/getUserByUserName", method = RequestMethod.GET)
    AuthUserInfoVo getUserByUserName(@RequestParam("username") String username);

    @LoadBalanced
    @RequestMapping(value = "user/getUserNameByWeChatCode", method = RequestMethod.GET)
    Result getUserNameByWeChatCode(@RequestParam("code") String code);

    @LoadBalanced
    @RequestMapping(value = "menu/getSysPermission", method = RequestMethod.GET)
    Result getSysPermission();

    @LoadBalanced
    @RequestMapping(value = "logRequest/save", method = RequestMethod.POST)
    Result save(@RequestBody LogRequestVo logRequestVo);

    @LoadBalanced
    @RequestMapping(value = "logLogin/save", method = RequestMethod.POST)
    Result save(@RequestBody LogLoginVo logLoginVo);

    @LoadBalanced
    @RequestMapping(value = "logSMS/save", method = RequestMethod.POST)
    Result save(@RequestBody LogSMSVo logSMSVo);
}
