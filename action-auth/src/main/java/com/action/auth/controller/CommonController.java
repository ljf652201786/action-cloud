package com.action.auth.controller;

import com.action.auth.holder.CaptchaProperties;
import com.action.common.core.entity.Captcha;
import com.action.common.core.enums.CaptchaTypeEnum;
import com.action.common.core.service.CaptchaService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 公共接口类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/26
 */
@RestController
@RequestMapping("common")
@AllArgsConstructor
public class CommonController {
    private final CaptchaService captchaService;
    private final CaptchaProperties captchaProperties;

    @SneakyThrows
    @RequestMapping(value = "getCaptcha", method = RequestMethod.GET)
    public void getCaptcha(HttpServletResponse response) {
        String[] code = captchaService.createCode(CaptchaTypeEnum.ALPHANUMERIC, captchaProperties.getCount());
        Captcha defaultCaptcha = captchaService.createDefaultCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), code, captchaProperties.getLineCount());
        captchaService.write(defaultCaptcha, response);
    }
}
