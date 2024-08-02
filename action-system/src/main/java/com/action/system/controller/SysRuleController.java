package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.struct.entity.SysMenuRule;
import com.action.system.struct.entity.SysRule;
import com.action.system.service.ISysMenuRuleService;
import com.action.system.service.ISysRuleService;
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
    public Result getInfo(String id) {
        return this.getInfoById(iSysRuleService, id);
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
        boolean isSave = iSysRuleService.save(sysRule);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
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
        boolean isUpdate = iSysRuleService.updateById(sysRule);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
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
        return this.deleteByIds(iSysRuleService, ids, (id) -> (iSysMenuRuleService.count(this.getLambdaQueryWrapper(new SysMenuRule()).eq(SysMenuRule::getRuleId, id))) == 0);
    }
}
