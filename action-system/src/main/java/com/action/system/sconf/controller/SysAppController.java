package com.action.system.sconf.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.sconf.service.ISysAppService;
import com.action.system.sconf.struct.entity.SysApp;
import com.action.system.sconf.struct.vo.AppVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 应用管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("app")
@RequiredArgsConstructor
public class SysAppController implements BaseController<ISysAppService, SysApp> {
    private final ISysAppService iSysAppService;

    /**
     * @param query 查询对象
     * @Description: app列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysApp sysApp, BaseQuery query) {
        return this.page(iSysAppService, sysApp, query, AppVo.class);
    }

    /**
     * @param id 应用id
     * @Description: 获取app数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Result getInfo(@RequestParam("id") String id) {
        return this.getInfoById(iSysAppService, id);
    }

    /**
     * @param sysApp 应用对象
     * @Description: 保存app数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysApp sysApp) {
        return Result.judge(iSysAppService.saveInfo(sysApp));
    }

    /**
     * @param sysApp 应用对象
     * @Description: 更新App数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysApp sysApp) {
        return Result.judge(iSysAppService.updateInfo(sysApp));
    }

    /**
     * @param ids app id集合
     * @Description: 批量删除app数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysAppService, ids, (sysApp) -> StatusType.DISABLED.getStatus().equals(sysApp.getStatus()));
    }

    /**
     * @param id app id
     * @Description: 禁用 app
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable("id") String id) {
        return Result.judge(iSysAppService.disable(id));
    }

    /**
     * @param id app id
     * @Description: 激活 app
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable("id") String id) {
        return Result.judge(iSysAppService.enable(id));
    }
}
