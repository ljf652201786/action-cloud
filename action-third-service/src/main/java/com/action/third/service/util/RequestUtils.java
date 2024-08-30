package com.action.third.service.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description: request工具类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
public class RequestUtils {
    public static HttpServletRequest getCurrentRequest(){
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
