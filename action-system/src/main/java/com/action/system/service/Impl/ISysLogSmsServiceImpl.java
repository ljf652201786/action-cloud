package com.action.system.service.Impl;

import com.action.system.entity.SysLogSms;
import com.action.system.mapper.SysLogSmsMapper;
import com.action.system.service.ISysLogSmsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 请求短信日志表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysLogSmsServiceImpl extends ServiceImpl<SysLogSmsMapper, SysLogSms> implements ISysLogSmsService {
    private final SysLogSmsMapper sysLogSmsMapper;
}
