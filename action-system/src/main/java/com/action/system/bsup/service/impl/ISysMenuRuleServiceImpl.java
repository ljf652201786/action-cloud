package com.action.system.bsup.service.impl;

import com.action.system.manager.struct.dto.RuleAllocationDto;
import com.action.system.bsup.struct.entity.SysMenuRule;
import com.action.system.bsup.mapper.SysMenuRuleMapper;
import com.action.system.bsup.service.ISysMenuRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 菜单规则中间表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysMenuRuleServiceImpl extends ServiceImpl<SysMenuRuleMapper, SysMenuRule> implements ISysMenuRuleService {
    private final SysMenuRuleMapper sysMenuRuleMapper;

    @Override
    public List<String> getRuleIdsByMenuId(String menuId) {
        List<SysMenuRule> sysMenuRules = this.list(this.getLambdaQueryWrapper().eq(SysMenuRule::getMenuId, menuId));
        if (CollectionUtils.isEmpty(sysMenuRules)) {
            return List.of();
        }
        return sysMenuRules.stream().map(SysMenuRule::getRuleId).collect(Collectors.toList());
    }

    @Override
    public boolean allocationRule(RuleAllocationDto ruleAllocationDto) {
        this.remove(this.getLambdaQueryWrapper().eq(SysMenuRule::getMenuId, ruleAllocationDto.getMenuId()));
        if (!CollectionUtils.isEmpty(ruleAllocationDto.getRuleIds())) {
            List<SysMenuRule> sysMenuRuleList = ruleAllocationDto.getRuleIds().stream().map(ruleId -> {
                return new SysMenuRule(ruleAllocationDto.getMenuId(), ruleId);
            }).collect(Collectors.toList());
            return this.saveBatch(sysMenuRuleList);
        }
        return true;
    }
}
