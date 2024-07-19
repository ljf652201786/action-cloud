package com.action.system.service.Impl;

import com.action.system.struct.entity.SysRule;
import com.action.system.mapper.SysRuleMapper;
import com.action.system.service.ISysRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 规则表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysRuleServiceImpl extends ServiceImpl<SysRuleMapper, SysRule> implements ISysRuleService {
    private final SysRuleMapper sysRuleMapper;

}
