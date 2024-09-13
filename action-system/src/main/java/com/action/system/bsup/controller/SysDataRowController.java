package com.action.system.bsup.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.system.bsup.struct.entity.SysDataRowLimit;
import com.action.system.bsup.service.ISysDataRowLimitService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param dataId 数据id
     * @Description: 根据dataId获取数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Result getInfo(@RequestParam("dataId") String dataId, @RequestParam("type") String type, @RequestParam("contactId") String contactId) {
        return Result.success(iSysDataRowLimitService.getInfo(dataId, type, contactId));
    }

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
        if (StringUtils.isEmpty(sysDataRowLimit.getDataId()) ||
                StringUtils.isEmpty(sysDataRowLimit.getContactId()) ||
                StringUtils.isEmpty(sysDataRowLimit.getType())) {
            return Result.failed("缺少必要数据");
        }
        return Result.judge(iSysDataRowLimitService.saveInfo(sysDataRowLimit));
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
        if (StringUtils.isEmpty(sysDataRowLimit.getDataId()) ||
                StringUtils.isEmpty(sysDataRowLimit.getContactId()) ||
                StringUtils.isEmpty(sysDataRowLimit.getType())) {
            return Result.failed("缺少必要数据");
        }
        return Result.judge(iSysDataRowLimitService.updateInfo(sysDataRowLimit));
    }

    /**
     * @param ids 数据行ids
     * @Description: 删除数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysDataRowLimitService, ids);
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
        return Result.judge(iSysDataRowLimitService.disable(id));
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
        return Result.judge(iSysDataRowLimitService.enable(id));
    }
}
