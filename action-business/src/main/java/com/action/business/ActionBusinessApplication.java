package com.action.business;

import com.action.call.annotation.EnableFeign;
import com.action.common.biz.annotation.EnableApiVersion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 业务服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableApiVersion
@EnableFeign
@SpringBootApplication
public class ActionBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionBusinessApplication.class, args);
        System.out.println("The service is fully operational *\\(^_^)/*");
    }
}


