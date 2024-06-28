package com.action.system.service.Impl;

import com.action.system.entity.SysLimitObj;
import com.action.system.mapper.SysLimitObjMapper;
import com.action.system.service.ISysLimitObjService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 限制对象表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysLimitObjServiceImpl extends ServiceImpl<SysLimitObjMapper, SysLimitObj> implements ISysLimitObjService {
    private final SysLimitObjMapper sysLimitObjMapper;

}
