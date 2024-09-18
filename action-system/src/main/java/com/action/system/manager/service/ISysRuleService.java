package com.action.system.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.entity.SysRule;

import java.util.List;


public interface ISysRuleService extends BaseMpService<SysRule> {
    List<SysRule> getInfoByMenuId(String menuId);

    boolean disable(String ruleId);

    boolean enable(String ruleId);
}
