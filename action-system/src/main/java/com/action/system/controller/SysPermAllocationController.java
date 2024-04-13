package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.common.enums.UseType;
import com.action.system.entity.SysMenuLimit;
import com.action.system.entity.SysMenuRule;
import com.action.system.service.ISysMenuLimitService;
import com.action.system.service.ISysMenuRuleService;
import com.action.system.vo.PermAllocationVo;
import com.action.system.vo.RuleAllocationVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 菜单权限管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("perm")
public class SysPermAllocationController {
    @Resource
    private ISysMenuLimitService iSysMenuLimitService;
    @Resource
    private ISysMenuRuleService iSysMenuRuleService;

    /**
     * @param permAllocationVo 分配对象
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "allocationMenu", method = RequestMethod.PUT)
    public Result allocationMenu(@RequestBody PermAllocationVo permAllocationVo) {
        if (StringUtils.isEmpty(permAllocationVo.getContactId()) || StringUtils.isEmpty(permAllocationVo.getType())) {
            return Result.error("请先选择所属分配对象");
        }
        boolean isRemove = iSysMenuLimitService.remove(new QueryWrapper<SysMenuLimit>().eq("type", permAllocationVo.getType()).eq("contactId", permAllocationVo.getContactId()));
        if (isRemove) {
            if (CollectionUtils.isEmpty(permAllocationVo.getMenuIds())) {
                return Result.error("请先选择分配的菜单");
            }
            List<SysMenuLimit> sysMenuLimitList = permAllocationVo.getMenuIds().stream().map(menuId -> {
                return new SysMenuLimit(permAllocationVo.getType(), permAllocationVo.getContactId(), menuId, UseType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            boolean isSaveBatch = iSysMenuLimitService.saveBatch(sysMenuLimitList);
            if (isSaveBatch) {
                return Result.success("保存数据成功");
            }
        }
        return Result.error("数据保存失败");
    }

    /**
     * @param ruleAllocationVo 分配权限规则
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "allocationRule", method = RequestMethod.PUT)
    public Result allocationRule(@RequestBody RuleAllocationVo ruleAllocationVo) {
        if (StringUtils.isEmpty(ruleAllocationVo.getMenuId())) {
            return Result.error("请先选择所属分配对象");
        }
        boolean isRemove = iSysMenuRuleService.remove(new QueryWrapper<SysMenuRule>().eq("menu_id", ruleAllocationVo.getMenuId()));
        if (isRemove) {
            if (!CollectionUtils.isEmpty(ruleAllocationVo.getRuleIds())) {
                List<SysMenuRule> sysMenuRuleList = ruleAllocationVo.getRuleIds().stream().map(ruleId -> {
                    return new SysMenuRule(ruleAllocationVo.getMenuId(), ruleId);
                }).collect(Collectors.toList());
                boolean isSaveBatch = iSysMenuRuleService.saveBatch(sysMenuRuleList);
                if (isSaveBatch) {
                    return Result.success("保存数据成功");
                }
            }
        }
        return Result.error("数据保存失败");
    }
}
