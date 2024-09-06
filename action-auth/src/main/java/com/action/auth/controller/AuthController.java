package com.action.auth.controller;

import com.action.auth.properties.CaptchaProperties;
import com.action.auth.service.ISmsService;
import com.action.common.core.common.Result;
import com.action.common.core.entity.Captcha;
import com.action.common.core.enums.CaptchaTypeEnum;
import com.action.common.core.service.CaptchaService;
import com.action.common.core.service.MailService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CaptchaService captchaService;
    private final CaptchaProperties captchaProperties;
    private final ISmsService iSmsService;
    private final MailService mailService;

    @SneakyThrows
    @RequestMapping(value = "getCaptcha", method = RequestMethod.GET)
    public void getCaptcha(HttpServletResponse response) {
        String[] code = captchaService.createCode(CaptchaTypeEnum.ALPHANUMERIC, captchaProperties.getCount());
        Captcha defaultCaptcha = captchaService.createDefaultCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), code, captchaProperties.getLineCount());
        captchaService.write(defaultCaptcha, response);
    }

    @RequestMapping(value = "sendSms", method = RequestMethod.POST)
    public Result sendLoginSmsCode(@RequestParam String phone) {
        boolean isSend = iSmsService.sendSmsCode(phone);
        return Result.judge(isSend);
    }

    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public Result sendEmail(@RequestParam String email) {
        mailService.sendSimpleEmail("652210786@qq.com", "主题", "内容");
//        mailService.sendTemplateEmail("2521313275@qq.com",new String[]{"652210786@qq.com"},new String[]{},"主题",);
        return Result.success();
    }

}
