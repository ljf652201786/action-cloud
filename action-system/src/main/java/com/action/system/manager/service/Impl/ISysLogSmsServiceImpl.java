package com.action.system.manager.service.Impl;

import com.action.system.manager.service.ISysLogSmsService;
import com.action.system.manager.struct.entity.SysLogSms;
import com.action.system.manager.mapper.SysLogSmsMapper;
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
