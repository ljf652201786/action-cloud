package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.bsup.struct.entity.SysMenuRule;
import com.action.system.manager.struct.entity.SysRule;
import com.action.system.bsup.service.ISysMenuRuleService;
import com.action.system.manager.service.ISysRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Description: 规则管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("rule")
@RequiredArgsConstructor
public class SysRuleController implements BaseController<ISysRuleService, SysRule> {
    private final ISysRuleService iSysRuleService;
    private final ISysMenuRuleService iSysMenuRuleService;

    /**
     * @param query 查询 查询参数
     * @Description: 规则列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysRule sysRule, BaseQuery query) {
        return this.page(iSysRuleService, sysRule, query);
    }

    /**
     * @param id 规则id
     * @Description: 获取规则详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Result getInfo(@RequestParam("id") String id) {
        return this.getInfoById(iSysRuleService, id);
    }

    /**
     * @param menuId 目录id
     * @Description: 获取规则详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfoByMenuId", method = RequestMethod.GET)
    public Result getInfoByMenuId(@RequestParam("menuId") String menuId) {
        return Result.success(iSysRuleService.getInfoByMenuId(menuId));
    }

    /**
     * @param sysRule 规则对象
     * @Description: 保存规则对象
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysRule sysRule) {
        SysRule sr = iSysRuleService.getOne(this.getLambdaQueryWrapper().eq(SysRule::getRuleCode, sysRule.getRuleCode()));
        if (Objects.nonNull(sr)) {
            return Result.failed("规则标识已存在");
        }
        return Result.judge(iSysRuleService.save(sysRule), "保存数据成功", "数据保存失败");
    }

    /**
     * @param sysRule 规则对象
     * @Description: 更新规则对象
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysRule sysRule) {
        SysRule oldRule = iSysRuleService.getById(sysRule.getId());
        SysRule sr = iSysRuleService.getOne(this.getLambdaQueryWrapper().eq(SysRule::getRuleCode, sysRule.getRuleCode()));
        if (!oldRule.getRuleCode().equals(sysRule.getRuleCode()) && Objects.nonNull(sr)) {
            return Result.failed("规则标识已使用");
        }
        return Result.judge(iSysRuleService.updateById(sysRule), "更新数据成功", "更新数据失败");
    }

    /**
     * @param ids 限制对象id集合
     * @Description: 批量删除限制对象数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysRuleService, ids, (sysRule) -> (iSysMenuRuleService.count(this.getLambdaQueryWrapper(new SysMenuRule()).eq(SysMenuRule::getRuleId, sysRule.getId()))) == 0);
    }

    /**
     * @param id 规则id
     * @Description: 禁用规则
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable("id") String id) {
        return Result.judge(iSysRuleService.disable(id));
    }

    /**
     * @param id 规则d
     * @Description: 激活规则
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable("id") String id) {
        return Result.judge(iSysRuleService.disable(id));
    }
}
