package com.action.system;

import com.action.call.annotation.EnableFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 系统服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableFeign
@SpringBootApplication(scanBasePackages = {"com.action.system"})
public class ActionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionSystemApplication.class, args);

    }
}


