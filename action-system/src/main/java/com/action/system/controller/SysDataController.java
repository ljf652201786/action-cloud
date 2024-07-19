package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;

import com.action.system.service.*;
import com.action.system.struct.entity.SysData;
import com.action.system.struct.entity.SysDataColumnLimit;
import com.action.system.struct.entity.SysDataRowLimit;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SysDataController implements BaseController<ISysDataService, SysData> {
    private final ISysDataService iSysDataService;
    private final ISysDataRowLimitService iSysDataRowLimitService;
    private final ISysDataColumnLimitService iSysDataColumnLimitService;

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
        SysData sd = iSysDataService.getOne(this.getLambdaQueryWrapper().eq(SysData::getDataCode, sysData.getDataCode()));
        if (Objects.nonNull(sd)) {
            return Result.failed("数据编码已存在");
        }
        boolean isSave = iSysDataService.save(sysData);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
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
        SysData sd = iSysDataService.getOne(this.getLambdaQueryWrapper().eq(SysData::getDataCode, sysData.getDataCode()));
        if (!oldData.getDataCode().equals(sysData.getDataCode()) && Objects.nonNull(sd)) {
            return Result.failed("数据编码已存在");
        }
        boolean isUpdate = iSysDataService.updateById(sysData);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
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
        SysDataRowLimit sysDataRowLimit = iSysDataRowLimitService.getOne(this.getLambdaQueryWrapper(new SysDataRowLimit()).eq(SysDataRowLimit::getDataId, id));
        SysDataColumnLimit sysDataColumnLimit = iSysDataColumnLimitService.getOne(this.getLambdaQueryWrapper(new SysDataColumnLimit()).eq(SysDataColumnLimit::getDataId, id));
        if (Objects.nonNull(sysDataRowLimit) || Objects.nonNull(sysDataColumnLimit)) {
            return Result.failed("该数据删除失败，因为包含正被使用");
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
