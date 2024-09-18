package com.action.system.manager.controller;

import com.action.call.struct.dto.LogSMSDto;
import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.manager.struct.entity.SysLogSms;
import com.action.system.manager.service.ISysLogSmsService;
import com.action.system.manager.struct.converter.LogSmsConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 请求短信日志管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("logSms")
@RequiredArgsConstructor
public class SysLogSmsController implements BaseController<ISysLogSmsService, SysLogSms> {
    private final ISysLogSmsService iSysLogSmsService;

    /**
     * @param query 查询对象
     * @Description: 短信日志列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysLogSms sysLogSms, BaseQuery query) {
        return this.page(iSysLogSmsService, sysLogSms, query);
    }

    /**
     * @param logSMSDto 请求短信日志对象
     * @Description: 保存请求日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody LogSMSDto logSMSDto) {
        return Result.judge(iSysLogSmsService.save(LogSmsConverter.INSTANCE.logSMSDtoToSysLogSms(logSMSDto)));
    }

    /**
     * @param ids 请求日志id集合
     * @Description: 批量删除短信日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysLogSmsService, ids);
    }
}
