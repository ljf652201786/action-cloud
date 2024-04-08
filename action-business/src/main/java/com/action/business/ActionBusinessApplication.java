package com.action.business;

import com.action.call.annotation.EnableFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 业务服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableFeign
@SpringBootApplication
public class ActionBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionBusinessApplication.class, args);

    }
}


