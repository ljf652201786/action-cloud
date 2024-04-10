package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.system.entity.*;
import com.action.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Description: 数据权限管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("data")
public class SysDataController {
    @Resource
    private ISysDataService iSysDataService;
    @Resource
    private ISysRowLimitService iSysRowLimitService;
    @Resource
    private ISysColumnLimitService iSysColumnLimitService;

    /**
     * @param sysData 数据对象
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysData sysData) {
        SysData sd = iSysDataService.getOne(new QueryWrapper<SysData>().eq("data_code", sysData.getDataCode()));
        if (Objects.nonNull(sd)) {
            return Result.error("数据编码已存在");
        }
        boolean isSave = iSysDataService.save(sysData);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
    }

    /**
     * @param sysData 数据对象
     * @Description: 更新数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysData sysData) {
        SysData oldData = iSysDataService.getById(sysData.getId());
        SysData sd = iSysDataService.getOne(new QueryWrapper<SysData>().eq("data_code", sysData.getDataCode()));
        if (!oldData.getDataCode().equals(sysData.getDataCode()) && Objects.nonNull(sd)) {
            return Result.error("数据编码已存在");
        }
        boolean isUpdate = iSysDataService.updateById(sysData);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.error("更新数据失败");
    }

    /**
     * @param id 数据id
     * @Description: 删除数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("id") String id) {
        SysRowLimit sysRowLimit = iSysRowLimitService.getOne(new QueryWrapper<SysRowLimit>().eq("data_id", id));
        SysColumnLimit sysColumnLimit = iSysColumnLimitService.getOne(new QueryWrapper<SysColumnLimit>().eq("data_id", id));
        if (Objects.nonNull(sysRowLimit) || Objects.nonNull(sysColumnLimit)) {
            return Result.error("该数据删除失败，因为包含正被使用");
        }
        iSysDataService.removeById(id);
        return Result.success("通过id删除数据成功");
    }

    /**
     * @Description: 数据树形选择
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "dataTreeSelect", method = RequestMethod.GET)
    public Result deptTreeSelect() {
        List<SysData> treeSelects = iSysDataService.buildDataTreeSelect();
        return Result.success("获取数据权限树成功", treeSelects);
    }
}
