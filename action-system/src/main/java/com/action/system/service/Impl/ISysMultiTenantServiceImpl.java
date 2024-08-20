package com.action.system.service.Impl;

import com.action.system.mapper.SysMultiTenantMapper;
import com.action.system.service.*;
import com.action.system.struct.entity.SysMultiTenant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 多租户表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysMultiTenantServiceImpl extends ServiceImpl<SysMultiTenantMapper, SysMultiTenant> implements ISysMultiTenantService {

}
