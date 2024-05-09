package com.action.gateway;

import com.action.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @Description: 网关服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableDiscoveryClient
@EnableApplication
public class ActionGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionGateWayApplication.class, args);

    }
}


