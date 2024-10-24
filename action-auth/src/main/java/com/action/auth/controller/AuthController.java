package com.action.auth.controller;

import com.action.auth.service.IAuthService;
import com.action.auth.service.ISmsService;
import com.action.common.captcha.service.impl.CaptchaStrategySelector;
import com.action.common.captcha.struct.dto.CaptchaDto;
import com.action.common.core.common.Result;
import com.action.common.core.service.MailService;
import com.action.common.core.tool.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.action.common.captcha.config.CaptchaConfig.DEFAULT_CAPTCHA_STRATEGY;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService iAuthService;
    private final CaptchaStrategySelector captchaStrategySelector;
    private final ISmsService iSmsService;
    private final MailService mailService;

    @GetMapping("/getCaptchaType")
    public Result getCaptchaType() {
        return Result.success(DEFAULT_CAPTCHA_STRATEGY);
    }

    @PostMapping("/getCaptcha")
    public Result getCaptcha(@RequestBody CaptchaDto captchaDto, HttpServletRequest request) {
        captchaDto.setClientUid(createClientUid(request));
        return captchaStrategySelector.select(captchaDto.getCaptchaType()).get(captchaDto);
    }

    @PostMapping("/checkCaptcha")
    public Result checkCaptcha(@RequestBody CaptchaDto captchaDto, HttpServletRequest request) {
        captchaDto.setClientUid(createClientUid(request));
        return captchaStrategySelector.select(captchaDto.getCaptchaType()).check(captchaDto);
    }

    @RequestMapping(value = "sendSms", method = RequestMethod.POST)
    public Result sendLoginSmsCode(@RequestParam String phone) {
        return Result.judge(iSmsService.sendSmsCode(phone));
    }

    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public Result sendEmail(@RequestParam String email) {
        mailService.sendSimpleEmail("652210786@qq.com", "主题", "内容");
//        mailService.sendTemplateEmail("2521313275@qq.com",new String[]{"652210786@qq.com"},new String[]{},"主题",);
        return Result.success();
    }

    @RequestMapping(value = "getCode", method = RequestMethod.GET)
    public void getCode(@RequestParam("appid") String appid,
                        @RequestParam("redirect_uri") String redirect_uri,
                        @RequestParam(value = "response_type", defaultValue = "code") String response_type,
                        @RequestParam(value = "scope", defaultValue = "openapi_login") String scope) {
//        redirect_uri = "http://127.0.0.1:6666/testcp/code";
        iAuthService.redirect(appid, redirect_uri, response_type, scope);
    }

    public static final String createClientUid(HttpServletRequest request) {
        String ip = IpUtils.getIpAdrress(request);
        String ua = request.getHeader("user-agent");
        if (StringUtils.isNotBlank(ip)) {
            return ip + ua;
        }
        return ua;
    }
}
