package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.system.dto.SysLogLoginQuery;
import com.action.system.entity.SysLogLogin;
import com.action.system.service.ISysLogLoginService;
import com.action.system.vo.LogLoginVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 登录日志管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("logLogin")
public class SysLogLoginController {
    @Autowired
    private ISysLogLoginService iSysLogLoginService;

    /**
     * @param query 查询对象
     * @Description: 字典列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result getDictList(SysLogLoginQuery query) {
        Page<SysLogLogin> rowPage = new Page<>(query.getPage(), query.getLimit());
        List<SysLogLogin> sysLogLoginList = iSysLogLoginService.list(rowPage, new QueryWrapper<>());
        return Result.success("获取登录日志列表", sysLogLoginList);
    }

    /**
     * @param logLoginVo 登录日志对象
     * @Description: 保存登录日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody LogLoginVo logLoginVo) {
        boolean isSave = iSysLogLoginService.save(logLoginVo.buildSysLogLogin());
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
    }

    /**
     * @param ids 登录日志id集合
     * @Description: 批量删除登录日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        boolean isRemove = iSysLogLoginService.removeBatchByIds(ids);
        if (isRemove) {
            return Result.success("批量通过id删除数据成功");
        }
        return Result.error("批量删除数据失败");
    }
}
