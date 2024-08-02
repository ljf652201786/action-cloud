package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.service.*;
import com.action.system.struct.entity.SysDept;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
        SysDept sd = iSysDeptService.getOne(this.getLambdaQueryWrapper().eq(SysDept::getDeptCode, sysDept.getDeptCode()));
        if (Objects.nonNull(sd)) {
            return Result.failed("部门编码已存在");
        }
        boolean isSave = iSysDeptService.save(sysDept);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
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
        SysDept oldDept = iSysDeptService.getById(sysDept.getId());
        SysDept sd = iSysDeptService.getOne(this.getLambdaQueryWrapper().eq(SysDept::getDeptCode, sysDept.getDeptCode()));
        if (!oldDept.getDeptCode().equals(sysDept.getDeptCode()) && Objects.nonNull(sd)) {
            return Result.failed("部门编码已存在");
        }
        boolean isUpdate = iSysDeptService.updateById(sysDept);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
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
        return this.deleteByIds(iSysDeptService, ids, (id) -> (iSysScopeService.deptNum(id)) == 0);
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
        SysDept sysDept = iSysDeptService.getById(id);
        if (Objects.isNull(sysDept)) {
            return Result.failed("该部门不存在");
        }
        sysDept.setStatus(StatusType.DISABLED.getStatus());
        iSysDeptService.updateById(sysDept);
        //更新scope表
        iSysScopeService.updateDeptStatus(id, StatusType.DISABLED.getStatus());
        return Result.success();
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
        SysDept sysDept = iSysDeptService.getById(id);
        if (Objects.isNull(sysDept)) {
            return Result.failed("该部门不存在");
        }
        sysDept.setStatus(StatusType.ENABLE.getStatus());
        iSysDeptService.updateById(sysDept);
        //更新scope表
        iSysScopeService.updateDeptStatus(id, StatusType.ENABLE.getStatus());
        return Result.success();
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
        List<SysDept> parentDeptList = iSysDeptService.buildDeptPostTreeSelect();
        return Result.success(parentDeptList);
    }
}
