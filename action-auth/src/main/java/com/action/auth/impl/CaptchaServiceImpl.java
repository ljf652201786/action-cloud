package com.action.auth.impl;

import com.action.common.core.entity.Captcha;
import com.action.common.core.service.CaptchaService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

/**
 * @Description: 验证码自定义实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/26
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Override
    public Captcha createCanvas(int i, int i1, String[] strings, Font font, boolean b, Integer integer, boolean b1, Float aFloat) throws IOException {
        return null;
    }
}
