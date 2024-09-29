package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.core.entity.LogRequestStruct;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.manager.service.ISysLogRequestService;
import com.action.system.manager.struct.converter.LogRequestConverter;
import com.action.system.manager.struct.entity.SysLogRequest;
import com.action.system.manager.struct.entity.SysUser;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Description: 请求日志管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("logRequest")
@RequiredArgsConstructor
public class SysLogRequestController implements BaseController<ISysLogRequestService, SysLogRequest> {
    private final ISysLogRequestService iSysLogRequestService;

    /**
     * @param query 查询对象
     * @Description: 请求日志列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysLogRequest sysLogRequest, BaseQuery query) {
        return this.page(iSysLogRequestService, sysLogRequest, query);
    }

    /**
     * @param logRequestStruct 请求日志对象
     * @Description: 保存请求日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody LogRequestStruct logRequestStruct) {
        return Result.judge(iSysLogRequestService.save(LogRequestConverter.INSTANCE.logRequestStructToSysLogRequest(logRequestStruct)), "保存数据成功", "数据保存失败");
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
        return this.deleteByIds(iSysLogRequestService, ids);
    }

    /**
     * @param response      response对象
     * @param sysLogRequest 操作日志对象
     * @Description: 导出操作日志excel文件
     * @return: void
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, SysLogRequest sysLogRequest) throws IOException {
        List<SysLogRequest> list = iSysLogRequestService.list(this.getQueryWrapper(sysLogRequest));
        this.exportExcel(response, SysUser.class, "操作日志导出列表.xlsx", "日志sheet表", list);
    }
}
