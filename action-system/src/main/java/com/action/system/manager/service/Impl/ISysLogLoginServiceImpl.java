package com.action.system.manager.service.Impl;

import com.action.system.manager.struct.entity.SysLogLogin;
import com.action.system.manager.mapper.SysLogLoginMapper;
import com.action.system.manager.service.ISysLogLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 登录日志表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysLogLoginServiceImpl extends ServiceImpl<SysLogLoginMapper, SysLogLogin> implements ISysLogLoginService {
    private final SysLogLoginMapper sysLogLoginMapper;
}
