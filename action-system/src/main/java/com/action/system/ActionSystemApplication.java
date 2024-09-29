package com.action.system;

import com.action.call.annotation.EnableFeign;
import com.action.common.annotation.EnableApplication;
import com.action.system.annotation.LoadActionSystemCore;
import com.action.system.config.ActionSystemCore;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

/**
 * @Description: 系统服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@LoadActionSystemCore
@EnableFeign
@EnableApplication
public class ActionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionSystemApplication.class, args);
        System.out.println("The service is fully operational *\\(^_^)/*");
    }
}


