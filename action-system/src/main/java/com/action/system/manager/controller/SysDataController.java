package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;

import com.action.system.bsup.service.ISysDataColumnLimitService;
import com.action.system.bsup.service.ISysDataRowLimitService;
import com.action.system.manager.service.ISysDataService;
import com.action.system.manager.struct.entity.SysData;
import com.action.system.bsup.struct.entity.SysDataColumnLimit;
import com.action.system.bsup.struct.entity.SysDataRowLimit;
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
        if (iSysDataService.checkDataCodeExist(sysData.getDataCode())) {
            return Result.failed("数据编码已存在");
        }
        return Result.judge(iSysDataService.save(sysData));
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
        return Result.judge(iSysDataService.updateInfo(sysData));
    }

    /**
     * @param ids 数据ids
     * @Description: 删除数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysDataService, ids, (sysData -> {
            SysDataRowLimit sysDataRowLimit = iSysDataRowLimitService.getOne(this.getLambdaQueryWrapper(new SysDataRowLimit()).eq(SysDataRowLimit::getDataId, sysData.getId()));
            SysDataColumnLimit sysDataColumnLimit = iSysDataColumnLimitService.getOne(this.getLambdaQueryWrapper(new SysDataColumnLimit()).eq(SysDataColumnLimit::getDataId, sysData.getId()));
            if (Objects.nonNull(sysDataRowLimit) || Objects.nonNull(sysDataColumnLimit)) {
                return false;
            }
            return true;
        }));
    }

    /**
     * @Description: 数据树形选择
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "dataTreeSelect", method = RequestMethod.GET)
    public Result deptTreeSelect(SysData sysData) {
        return this.treeSelect(iSysDataService, sysData, SysData::getId);
    }

    /**
     * @param id 数据id
     * @Description: 禁用数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable("id") String id) {
        return Result.judge(iSysDataService.disable(id));
    }

    /**
     * @param id 数据id
     * @Description: 激活数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable("id") String id) {
        return Result.judge(iSysDataService.enable(id));
    }
}
