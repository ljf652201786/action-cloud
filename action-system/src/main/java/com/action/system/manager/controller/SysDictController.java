package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.manager.struct.entity.SysDict;
import com.action.system.bsup.struct.entity.SysDictDetail;
import com.action.system.bsup.service.ISysDictDetailService;
import com.action.system.manager.service.ISysDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 字典管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("dict")
@RequiredArgsConstructor
public class SysDictController implements BaseController<ISysDictService, SysDict> {
    private final ISysDictService iSysDictService;
    private final ISysDictDetailService iSysDictDetailService;

    /**
     * @param query 查询对象
     * @Description: 字典列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysDict sysDict, BaseQuery query) {
        return this.page(iSysDictService, sysDict, query);
    }

    /**
     * @param sysDict 字典对象
     * @Description: 保存字典
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysDict sysDict) {
        if (iSysDictService.checkDictCodeExist(sysDict.getDictCode())) {
            return Result.failed("字典编码已存在");
        }
        return Result.judge(iSysDictService.save(sysDict));
    }

    /**
     * @param sysDict 字典对象
     * @Description: 更新字典
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysDict sysDict) {
        return Result.judge(iSysDictService.updateInfo(sysDict));
    }

    /**
     * @param ids 字典详情id集合
     * @Description: 批量删除字典
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysDictService, ids, (sysDict) -> (iSysDictDetailService.count(this.getLambdaQueryWrapper(new SysDictDetail()).eq(SysDictDetail::getDictId, sysDict.getId()))) == 0);
    }

    /**
     * @param id 字典id
     * @Description: 禁用字典
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable("id") String id) {
        return Result.judge(iSysDictService.disable(id));
    }

    /**
     * @param id 字典id
     * @Description: 激活字典
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable("id") String id) {
        return Result.judge(iSysDictService.enable(id));
    }
}
