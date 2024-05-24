package com.action.system;

import com.action.call.annotation.EnableFeign;
import com.action.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * @Description: 系统服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableFeign
@EnableApplication
public class ActionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionSystemApplication.class, args);
        System.out.println("The service is fully operational *\\(^_^)/*");
    }
}


