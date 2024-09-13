package com.action.system.bsup.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.system.bsup.struct.dto.ColumnAllocationDto;
import com.action.system.bsup.struct.entity.SysDataColumnLimit;
import com.action.system.bsup.service.ISysDataColumnLimitService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 数据列权限管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("dataColumn")
@RequiredArgsConstructor
public class SysDataColumnController implements BaseController<ISysDataColumnLimitService, SysDataColumnLimit> {
    private final ISysDataColumnLimitService iSysDataColumnLimitService;

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
        return Result.success(iSysDataColumnLimitService.getInfo(dataId, type, contactId));
    }

    /**
     * @param columnAllocationDto 数据列对象
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody ColumnAllocationDto columnAllocationDto) {
        if (StringUtils.isEmpty(columnAllocationDto.getDataId()) ||
                StringUtils.isEmpty(columnAllocationDto.getContactId()) ||
                StringUtils.isEmpty(columnAllocationDto.getType())) {
            return Result.failed("缺少必要数据");
        }
        return Result.judge(iSysDataColumnLimitService.saveInfo(columnAllocationDto));
    }
}
