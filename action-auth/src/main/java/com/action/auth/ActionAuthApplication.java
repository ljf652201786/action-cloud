package com.action.auth;

import com.action.call.annotation.EnableFeign;
import com.action.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * @Description: 认证服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableFeign
@EnableApplication
public class ActionAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionAuthApplication.class, args);
        System.out.println("The service is fully operational *\\(^_^)/*");
    }
}


