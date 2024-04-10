package com.action.system.service.Impl;

import com.action.system.entity.SysColumnLimit;
import com.action.system.mapper.SysColumnLimitMapper;
import com.action.system.service.ISysColumnLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 数据列权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysColumnLimitServiceImpl extends ServiceImpl<SysColumnLimitMapper, SysColumnLimit> implements ISysColumnLimitService {
    @Resource
    private SysColumnLimitMapper sysColumnLimitMapper;

}
