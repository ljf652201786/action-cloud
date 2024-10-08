package com.action.system.manager.service.Impl;

import com.action.system.manager.mapper.SysLogRequestMapper;
import com.action.system.manager.service.ISysLogRequestService;
import com.action.system.manager.struct.entity.SysLogRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 请求日志表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysLogRequestServiceImpl extends ServiceImpl<SysLogRequestMapper, SysLogRequest> implements ISysLogRequestService {
}
