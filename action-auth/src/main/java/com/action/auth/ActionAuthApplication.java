package com.action.auth;

import com.action.call.annotation.EnableFeign;
import com.action.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/*
 * 认证服务
 * */
@EnableFeign
@EnableApplication
public class ActionAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActionAuthApplication.class, args);

    }
}

