package com.action.system.service.Impl;

import com.action.system.entity.SysDataColumnLimit;
import com.action.system.mapper.SysDataColumnLimitMapper;
import com.action.system.service.ISysDataColumnLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 数据列权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysDataColumnLimitServiceImpl extends ServiceImpl<SysDataColumnLimitMapper, SysDataColumnLimit> implements ISysDataColumnLimitService {
    @Resource
    private SysDataColumnLimitMapper sysDataColumnLimitMapper;

}
