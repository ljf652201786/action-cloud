package com.action.auth;

import com.action.call.annotation.EnableFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 认证服务
 * */
@EnableFeign
@SpringBootApplication
public class ActionAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActionAuthApplication.class, args);

    }
}

