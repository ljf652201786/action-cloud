package com.action.system.service.Impl;

import com.action.system.entity.SysDataRowLimit;
import com.action.system.mapper.SysDataRowLimitMapper;
import com.action.system.service.ISysDataRowLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 数据行权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysDataRowLimitServiceImpl extends ServiceImpl<SysDataRowLimitMapper, SysDataRowLimit> implements ISysDataRowLimitService {
    @Resource
    private SysDataRowLimitMapper sysDataRowLimitMapper;

}
