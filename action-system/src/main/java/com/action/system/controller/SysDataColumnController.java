package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseController;
import com.action.system.entity.SysDataColumnLimit;
import com.action.system.service.ISysDataColumnLimitService;
import com.action.system.vo.ColumnAllocationVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
     * @param columnAllocationVo 数据列对象
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody ColumnAllocationVo columnAllocationVo) {
        if (StringUtils.isEmpty(columnAllocationVo.getDataId()) || StringUtils.isEmpty(columnAllocationVo.getContactId()) || StringUtils.isEmpty(columnAllocationVo.getType())) {
            return Result.failed("缺少必要数据");
        }

        iSysDataColumnLimitService.remove(((LambdaQueryWrapper<SysDataColumnLimit>) this.getLambdaQueryWrapper())
                .eq(SysDataColumnLimit::getDataId, columnAllocationVo.getDataId())
                .eq(SysDataColumnLimit::getContactId, columnAllocationVo.getContactId())
                .eq(SysDataColumnLimit::getType, columnAllocationVo.getType()));
        if (CollectionUtils.isEmpty(columnAllocationVo.getColumnVoList())) {
            return Result.success("保存数据成功");
        }
        List<SysDataColumnLimit> sysDataColumnLimitList = columnAllocationVo.getColumnVoList().stream().map(columnVo -> {
            return new SysDataColumnLimit(columnAllocationVo.getDataId(), columnAllocationVo.getType(), columnAllocationVo.getContactId(), columnVo.getField(), columnVo.getFieldDesc());
        }).collect(Collectors.toList());
        boolean isSave = iSysDataColumnLimitService.saveBatch(sysDataColumnLimitList);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
    }
}
