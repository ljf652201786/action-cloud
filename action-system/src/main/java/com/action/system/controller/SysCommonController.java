package com.action.system.controller;

import com.action.common.biz.exception.BizException;
import com.action.common.core.common.Result;
import com.action.common.core.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * @Description: 系统服务公共接口
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/20
 */
@RestController
@RequestMapping("common")
@RequiredArgsConstructor
public class SysCommonController {
    private final MailService mailService;

    /**
     * @Description: 用户邮箱注册
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */

    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public Result sendEmail() {
//        mailService.sendSimpleEmail("652210786@qq.com", "主题", "内容");
//        mailService.sendTemplateEmail("2521313275@qq.com",new String[]{"652210786@qq.com"},new String[]{},"主题",);
        return Result.success();
    }

    @PreAuthorize("@ss.hasPerm('sys:common:ese')")
    @RequestMapping(value = "e", method = RequestMethod.GET)
    public Result ese() {
        throw new BizException("234243");
    }
}
