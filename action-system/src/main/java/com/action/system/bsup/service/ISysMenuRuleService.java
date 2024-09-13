package com.action.system.bsup.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.dto.RuleAllocationDto;
import com.action.system.bsup.struct.entity.SysMenuRule;

import java.util.List;


public interface ISysMenuRuleService extends BaseMpService<SysMenuRule> {
    List<String> getRuleIdsByMenuId(String menuId);

    boolean allocationRule(RuleAllocationDto ruleAllocationDto);
}
