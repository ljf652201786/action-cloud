package com.action.auth.service.impl;

import com.action.auth.service.ISmsService;
import com.action.call.clients.RemoteSystemClients;
import com.action.call.struct.dto.LogSMSDto;
import com.action.common.core.handle.RedisCacheHandle;
import com.action.common.sms.properties.AliyunSmsProperties;
import com.action.common.sms.service.impl.AliyunSmsService;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

import static com.action.common.core.constants.ActionRedisConstants.REGISTER_SMS_CODE_KEY;

/**
 * @Description: 短信服务实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/17
 */
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements ISmsService {
    private static final Long expireTime = 5 * 60L;
    private final RedisCacheHandle redisCacheHandle;
    private final AliyunSmsProperties aliyunSmsProperties;
    private final AliyunSmsService aliyunSmsService;
    private final RemoteSystemClients remoteSystemClients;

    /**
     * 发送登录短信验证码
     *
     * @param phone 手机号
     * @return true|false 是否发送成功
     */
    @Override
    public boolean sendSmsCode(String phone) {
        // 获取短信模板代码
        String templateCode = aliyunSmsProperties.getTemplateCodes().get("login");
        // 生成随机4位数验证码
        String code = aliyunSmsService.generateCode();
        // 短信模板: 您的验证码：${code}，该验证码5分钟内有效，请勿泄漏于他人。
        // 其中 ${code} 是模板参数，使用时需要替换为实际值。
        String templateParams = JSONObject.toJSONString(Collections.singletonMap("code", code));
        boolean result = aliyunSmsService.sendSms(phone, templateCode, templateParams);
        if (result) {
            // 将验证码存入redis，有效期5分钟
            redisCacheHandle.set(REGISTER_SMS_CODE_KEY + phone, code, expireTime);
        }
        LogSMSDto logSMSDto = new LogSMSDto(phone, templateParams, result, new Date(), String.valueOf(expireTime));
        remoteSystemClients.save(logSMSDto);
        return result;
    }
}
