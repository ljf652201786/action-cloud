package com.action.call.config;

import com.action.call.decoder.FeignDecoder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: OpenFeign 自定义响应解码器配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/22
 */
@Configuration
public class FeignDecoderConfig implements RequestInterceptor {
    private static final List<String> ignoreUrls = List.of("/user/getUserByUserName");

    @Bean
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverters> messageConverters) {
        return new OptionalDecoder((new ResponseEntityDecoder(new FeignDecoder(new SpringDecoder(messageConverters)))));
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            // 从当前请求的请求头中获取Token（假设Token在名为"Authorization"的请求头中）
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (token != null && !ignoreUrls.contains(request.getRequestURI())) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, token);
            }
        }
    }

}
