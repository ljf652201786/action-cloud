package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysScope;
import com.action.system.mapper.SysScopeMapper;
import com.action.system.service.ISysScopeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 范围表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysScopeServiceImpl extends ServiceImpl<SysScopeMapper, SysScope> implements ISysScopeService {
    @Resource
    private SysScopeMapper sysScopeMapper;

    public List<SysScope> getSysScopeByUserId(String userId) {
        return sysScopeMapper.selectList(new QueryWrapper<SysScope>().eq("user_id", userId).eq("status", UseType.ENABLE.getStatus()));
    }
}
