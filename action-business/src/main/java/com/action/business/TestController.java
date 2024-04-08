package com.action.business;

import com.action.call.clients.RemoteSystemClients;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/01
 */
@RefreshScope
@RestController
@RequestMapping("test")
public class TestController {

    @Value("${username}")
    public String usrname;
    @Resource
    private RemoteSystemClients systemClients;

    @RequestMapping(value = "t", method = RequestMethod.GET)
    public String kk() {
        System.out.println("123131" + this.usrname);
//        String u = systemClients.u();
//        System.out.println(u);
        return "调用成功啦";
    }
}
