package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.system.entity.SysColumnLimit;
import com.action.system.service.ISysColumnLimitService;
import com.action.system.vo.ColumnAllocationVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
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
public class SysDataColumnController {
    @Resource
    private ISysColumnLimitService iSysColumnLimitService;

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
            return Result.error("缺少必要数据");
        }
        iSysColumnLimitService.remove(new QueryWrapper<SysColumnLimit>().eq("data_id", columnAllocationVo.getDataId()).eq("contact_id", columnAllocationVo.getContactId()).eq("type", columnAllocationVo.getType()));
        if (CollectionUtils.isEmpty(columnAllocationVo.getColumnVoList())) {
            return Result.success("保存数据成功");
        }
        List<SysColumnLimit> sysColumnLimitList = columnAllocationVo.getColumnVoList().stream().map(columnVo -> {
            return new SysColumnLimit(columnAllocationVo.getDataId(), columnAllocationVo.getType(), columnAllocationVo.getContactId(), columnVo.getField(), columnVo.getFieldDesc());
        }).collect(Collectors.toList());
        boolean isSave = iSysColumnLimitService.saveBatch(sysColumnLimitList);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
    }
}
