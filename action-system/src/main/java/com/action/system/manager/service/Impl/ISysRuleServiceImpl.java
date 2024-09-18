package com.action.system.manager.service.Impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.service.ISysMenuRuleService;
import com.action.system.manager.service.ISysRuleService;
import com.action.system.manager.struct.entity.SysRule;
import com.action.system.manager.mapper.SysRuleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description: 规则表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysRuleServiceImpl extends ServiceImpl<SysRuleMapper, SysRule> implements ISysRuleService {
    private final SysRuleMapper sysRuleMapper;
    private final ISysMenuRuleService iSysMenuRuleService;


    @Override
    public List<SysRule> getInfoByMenuId(String menuId) {
        List<String> ruleIds = iSysMenuRuleService.getRuleIdsByMenuId(menuId);
        if (CollectionUtils.isEmpty(ruleIds)) {
            return null;
        }
        return this.list(this.getLambdaQueryWrapper().in(SysRule::getId, ruleIds));
    }

    @Override
    public boolean disable(String ruleId) {
        return this.update(this.getLambdaUpdateWrapper().set(SysRule::getStatus, StatusType.DISABLED.getStatus()).eq(SysRule::getId, ruleId));
    }

    @Override
    public boolean enable(String ruleId) {
        return this.update(this.getLambdaUpdateWrapper().set(SysRule::getStatus, StatusType.ENABLE.getStatus()).eq(SysRule::getId, ruleId));
    }
}
