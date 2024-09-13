package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.system.manager.struct.dto.PermAllocationDto;
import com.action.system.bsup.struct.entity.SysMenuLimit;
import com.action.system.bsup.service.ISysMenuLimitService;
import com.action.system.bsup.service.ISysMenuRuleService;
import com.action.system.manager.struct.dto.RuleAllocationDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 菜单权限管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("perm")
@RequiredArgsConstructor
public class SysPermAllocationController implements BaseController {
    private final ISysMenuLimitService iSysMenuLimitService;
    private final ISysMenuRuleService iSysMenuRuleService;

    /**
     * @param permAllocationDto 分配对象
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "allocationMenu", method = RequestMethod.PUT)
    public Result allocationMenu(@RequestBody PermAllocationDto permAllocationDto) {
        if (StringUtils.isEmpty(permAllocationDto.getContactId()) || StringUtils.isEmpty(permAllocationDto.getType())) {
            return Result.failed("请先选择所属分配对象");
        }
        return Result.judge(iSysMenuLimitService.allocationMenu(permAllocationDto));
    }

    /**
     * @param type      分配对象类型
     * @param contactId 关联id
     * @Description: 获取分配的目录权限
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getAllocationMenu", method = RequestMethod.GET)
    public Result getAllocationMenu(@RequestParam("type") String type, @RequestParam("contactId") String contactId) {
        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(contactId)) {
            return Result.failed("请先选择所属分配对象");
        }
        return Result.success(iSysMenuLimitService.getAllocationMenu(type, contactId));
    }

    /**
     * @param ruleAllocationDto 分配权限规则
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "allocationRule", method = RequestMethod.PUT)
    public Result allocationRule(@RequestBody RuleAllocationDto ruleAllocationDto) {
        return Result.judge(iSysMenuRuleService.allocationRule(ruleAllocationDto));
    }
}
