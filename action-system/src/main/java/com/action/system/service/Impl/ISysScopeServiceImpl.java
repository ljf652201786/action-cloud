package com.action.system.service.Impl;

import com.action.system.entity.SysScope;
import com.action.system.mapper.SysScopeMapper;
import com.action.system.service.ISysScopeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 范围表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysScopeServiceImpl extends ServiceImpl<SysScopeMapper, SysScope> implements ISysScopeService {
    @Resource
    private SysScopeMapper sysScopeMapper;

}
