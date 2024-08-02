package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.struct.converter.LogLoginConverter;
import com.action.system.struct.entity.SysLogLogin;
import com.action.system.service.ISysLogLoginService;
import com.action.call.vo.LogLoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 登录日志管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("logLogin")
@RequiredArgsConstructor
public class SysLogLoginController implements BaseController<ISysLogLoginService, SysLogLogin> {
    private final ISysLogLoginService iSysLogLoginService;

    /**
     * @param query 查询对象
     * @Description: 字典列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result getDictList(SysLogLogin sysLogLogin, BaseQuery query) {
        return this.page(iSysLogLoginService, sysLogLogin, query);
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
        boolean isSave = iSysLogLoginService.save(LogLoginConverter.INSTANCE.logLoginVoToSysLogLogin(logLoginVo));
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
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
        return this.deleteByIds(iSysLogLoginService, ids);
    }
}
