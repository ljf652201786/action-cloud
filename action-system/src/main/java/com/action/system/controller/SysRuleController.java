package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.system.dto.SysRuleQuery;
import com.action.system.entity.SysMenuLimit;
import com.action.system.entity.SysRule;
import com.action.system.service.ISysMenuLimitService;
import com.action.system.service.ISysRuleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 规则管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("rule")
public class SysRuleController {
    @Resource
    private ISysRuleService iSysRuleService;
    @Resource
    private ISysMenuLimitService iSysMenuLimitService;

    /**
     * @param query 查询 查询参数
     * @Description: 规则列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysRuleQuery query) {
        Page<SysRule> rowPage = new Page(query.getPage(), query.getLimit());
        List<SysRule> sysLimitObjList = iSysRuleService.list(rowPage, new QueryWrapper<>());
        return Result.success("分页获取系统管理-规则表列表成功", sysLimitObjList);
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
        SysRule sysRule = iSysRuleService.getById(id);
        return Result.success("获取数据成功", sysRule);
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
        SysRule sr = iSysRuleService.getOne(new QueryWrapper<SysRule>().eq("rule_code", sysRule.getRuleCode()));
        if (Objects.nonNull(sr)) {
            return Result.error("规则标识已存在");
        }
        boolean isSave = iSysRuleService.save(sysRule);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
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
        SysRule sr = iSysRuleService.getOne(new QueryWrapper<SysRule>().eq("rule_code", sysRule.getRuleCode()));
        if (!oldRule.getRuleCode().equals(sysRule.getRuleCode()) && Objects.nonNull(sr)) {
            return Result.error("规则标识已使用");
        }
        boolean isUpdate = iSysRuleService.updateById(sysRule);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.error("更新数据失败");
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
        List<String> idList = new ArrayList<>();
        List<String> idExistList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            long num = iSysMenuLimitService.count(new QueryWrapper<SysMenuLimit>().eq("rule_id", ids.get(i)));
            if (num == 0) {
                idList.add(ids.get(i));
            } else {
                idExistList.add(ids.get(i));
            }
        }
        if (idList.size() > 0) {
            iSysRuleService.removeBatchByIds(idList);
        }
        if (idExistList.size() > 0) {
            return Result.error("删除失败，因为包含正被用户使用", idExistList);
        }
        return Result.success("批量通过id删除数据成功");
    }
}
