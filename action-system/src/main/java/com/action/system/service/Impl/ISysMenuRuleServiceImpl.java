package com.action.system.service.Impl;

import com.action.system.entity.SysMenuRule;
import com.action.system.mapper.SysMenuRuleMapper;
import com.action.system.service.ISysMenuRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 菜单规则中间表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysMenuRuleServiceImpl extends ServiceImpl<SysMenuRuleMapper, SysMenuRule> implements ISysMenuRuleService {
    private final SysMenuRuleMapper sysMenuRuleMapper;

}
