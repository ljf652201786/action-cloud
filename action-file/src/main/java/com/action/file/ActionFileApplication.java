package com.action.file;

import com.action.call.annotation.EnableFeign;
import com.action.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * @Description: 文件服务
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/1
 */
@EnableFeign
@EnableApplication
public class ActionFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionFileApplication.class, args);

    }
}


