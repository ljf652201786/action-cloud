package com.action.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Description: 网关服务
 *
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.action"})
public class ActionGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionGateWayApplication.class, args);

    }
}


