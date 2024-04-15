package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.system.dto.SysLogRequestQuery;
import com.action.system.entity.SysLogRequest;
import com.action.system.service.ISysLogRequestService;
import com.action.call.vo.LogRequestVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 请求日志管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("logRequest")
public class SysLogRequestController {
    @Autowired
    private ISysLogRequestService iSysLogRequestService;

    /**
     * @param query 查询对象
     * @Description: 字典列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result getDictList(SysLogRequestQuery query) {
        Page<SysLogRequest> rowPage = new Page<>(query.getPage(), query.getLimit());
        List<SysLogRequest> sysLogLoginList = iSysLogRequestService.list(rowPage, new QueryWrapper<>());
        return Result.success("获取请求日志列表", sysLogLoginList);
    }

    /**
     * @param logRequestVo 请求日志对象
     * @Description: 保存请求日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody LogRequestVo logRequestVo) {
        boolean isSave = iSysLogRequestService.save(new SysLogRequest().buildSysLogRequest(logRequestVo));
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
    }

    /**
     * @param ids 请求日志id集合
     * @Description: 批量删除请求日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        boolean isRemove = iSysLogRequestService.removeBatchByIds(ids);
        if (isRemove) {
            return Result.success("批量通过id删除数据成功");
        }
        return Result.error("批量删除数据失败");
    }
}
