package com.action.system.bsup.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.system.bsup.service.ICommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 系统公共方法
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/11
 */
@RestController
@RequestMapping("commont")
@RequiredArgsConstructor
public class SysCommonController implements BaseController {
    private final ICommonService iCommonService;

    /*
     * 获取数据库表名
     * */
    @RequestMapping(value = "getTables", method = RequestMethod.GET)
    public Result getTables(/*@RequestParam("database") String database*/) {
        return Result.success(iCommonService.getTables());
    }

    /*
     * 获取数据库表名
     * */
    @RequestMapping(value = "getTableColumn", method = RequestMethod.GET)
    public Result getTableColumn(@RequestParam("tableName") String tableName) {
        return Result.success(iCommonService.getTableColumn(tableName));
    }
}
