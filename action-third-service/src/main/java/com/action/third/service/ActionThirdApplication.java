package com.action.third.service;

import com.action.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * @Description: 第三方api服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableApplication
public class ActionThirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionThirdApplication.class, args);
        System.out.println("The service is fully operational *\\(^_^)/*");
    }
}


