package com.action.system.service.Impl;

import com.action.system.entity.SysLogRequest;
import com.action.system.mapper.SysLogRequestMapper;
import com.action.system.service.ISysLogRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 请求日志表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysLogRequestServiceImpl extends ServiceImpl<SysLogRequestMapper, SysLogRequest> implements ISysLogRequestService {
    @Resource
    private SysLogRequestMapper sysLogRequestMapper;
}
