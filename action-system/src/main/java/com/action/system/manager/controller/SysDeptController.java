package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.bsup.service.ISysScopeService;
import com.action.system.manager.service.ISysDeptService;
import com.action.system.manager.struct.entity.SysDept;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 部门管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("dept")
@RequiredArgsConstructor
public class SysDeptController implements BaseController<ISysDeptService, SysDept> {
    private final ISysDeptService iSysDeptService;
    private final ISysScopeService iSysScopeService;

    /**
     * @param query 查询对象
     * @Description: 部门列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysDept sysDept, BaseQuery query) {
        return this.page(iSysDeptService, sysDept, query);
    }

    /**
     * @param sysDept 部门对象
     * @Description: 保存部门数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysDept sysDept) {
        if (iSysDeptService.checkDeptCodeExist(sysDept.getDeptCode())) {
            return Result.failed("部门编码已存在");
        }
        return Result.judge(iSysDeptService.save(sysDept));
    }

    /**
     * @param sysDept 部门对象
     * @Description: 更新部门数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysDept sysDept) {
        return Result.judge(iSysDeptService.updateInfo(sysDept));
    }

    /**
     * @param ids 部门id集合
     * @Description: 批量删除部门数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysDeptService, ids, (sysDept) -> (iSysScopeService.deptNum(sysDept.getId())) == 0);
    }

    /**
     * @param id 部门id
     * @Description: 禁用部门
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable("id") String id) {
        return Result.judge(iSysDeptService.disable(id));
    }

    /**
     * @param id 部门id
     * @Description: 激活部门
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable("id") String id) {
        return Result.judge(iSysDeptService.enable(id));
    }

    /**
     * @Description: 部门树形选择
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deptTreeSelect", method = RequestMethod.GET)
    public Result deptTreeSelect(SysDept sysDept) {
        return this.treeSelect(iSysDeptService, sysDept, SysDept::getId);
    }

    /**
     * @Description: 部门岗位树形选择
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deptPostTreeSelect", method = RequestMethod.GET)
    public Result deptPostTreeSelect() {
        return Result.success(iSysDeptService.buildDeptPostTreeSelect());
    }
}
