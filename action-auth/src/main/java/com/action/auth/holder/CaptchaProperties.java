package com.action.auth.holder;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 验证码参数配置类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/26
 */
@ConfigurationProperties(
        prefix = "spring.captcha"
)
public class CaptchaProperties {
    private Integer width = 230;
    private Integer height = 60;
    private Integer count = 6;
    private Integer lineCount = 20;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLineCount() {
        return lineCount;
    }

    public void setLineCount(Integer lineCount) {
        this.lineCount = lineCount;
    }
}
