package com.action.system.service.Impl;

import com.action.system.entity.SysRowLimit;
import com.action.system.mapper.SysRowLimitMapper;
import com.action.system.service.ISysRowLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 数据行权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysRowLimitServiceImpl extends ServiceImpl<SysRowLimitMapper, SysRowLimit> implements ISysRowLimitService {
    @Resource
    private SysRowLimitMapper sysRowLimitMapper;

}
