package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.enums.StatusType;
import com.action.system.struct.entity.SysDataRowLimit;
import com.action.system.service.ISysDataRowLimitService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Description: 数据行权限管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("dataRow")
@RequiredArgsConstructor
public class SysDataRowController implements BaseController<ISysDataRowLimitService, SysDataRowLimit> {
    private final ISysDataRowLimitService iSysDataRowLimitService;

    /**
     * @param sysDataRowLimit 数据行对象
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysDataRowLimit sysDataRowLimit) {
        if (StringUtils.isEmpty(sysDataRowLimit.getDataId()) || StringUtils.isEmpty(sysDataRowLimit.getContactId()) || StringUtils.isEmpty(sysDataRowLimit.getType())) {
            return Result.failed("缺少必要数据");
        }
        sysDataRowLimit.setStatus(StatusType.ENABLE.getStatus());
        sysDataRowLimit.setRelation("and");
        boolean isSave = iSysDataRowLimitService.save(sysDataRowLimit);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
    }

    /**
     * @param sysDataRowLimit 数据行对象
     * @Description: 更新数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysDataRowLimit sysDataRowLimit) {
        if (StringUtils.isEmpty(sysDataRowLimit.getDataId()) || StringUtils.isEmpty(sysDataRowLimit.getContactId()) || StringUtils.isEmpty(sysDataRowLimit.getType())) {
            return Result.failed("缺少必要数据");
        }
        sysDataRowLimit.setStatus(StatusType.ENABLE.getStatus());
        sysDataRowLimit.setRelation("and");
        boolean isUpdate = iSysDataRowLimitService.updateById(sysDataRowLimit);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
    }

    /**
     * @param id 数据行id
     * @Description: 删除数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteById(@RequestParam("id") String id) {
        iSysDataRowLimitService.removeById(id);
        return Result.success("通过id删除数据成功");
    }

    /**
     * @param id 数据行id
     * @Description: 禁用数据行限制
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable("id") String id) {
        SysDataRowLimit sysDataRowLimit = iSysDataRowLimitService.getById(id);
        if (Objects.isNull(sysDataRowLimit)) {
            return Result.failed("该数据不存在");
        }
        sysDataRowLimit.setStatus(StatusType.DISABLED.getStatus());
        iSysDataRowLimitService.updateById(sysDataRowLimit);
        iSysDataRowLimitService.updateDataRowLimitStatus(id, StatusType.DISABLED.getStatus());
        return Result.success("禁用成功");
    }

    /**
     * @param id 岗位id
     * @Description: 激活岗位
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable("id") String id) {
        SysDataRowLimit sysDataRowLimit = iSysDataRowLimitService.getById(id);
        if (Objects.isNull(sysDataRowLimit)) {
            return Result.failed("该数据不存在");
        }
        sysDataRowLimit.setStatus(StatusType.ENABLE.getStatus());
        iSysDataRowLimitService.updateById(sysDataRowLimit);
        iSysDataRowLimitService.updateDataRowLimitStatus(id, StatusType.ENABLE.getStatus());
        return Result.success("激活成功");
    }
}
