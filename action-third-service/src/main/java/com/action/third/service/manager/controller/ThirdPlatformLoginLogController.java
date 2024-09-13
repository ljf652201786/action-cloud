package com.action.third.service.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.third.service.manager.service.IThirdPlatformLoginLogService;
import com.action.third.service.manager.struct.entity.ThirdPlatformLoginLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 第三方平台登录日志控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@RequestMapping("login-log")
@RestController
@RequiredArgsConstructor
public class ThirdPlatformLoginLogController implements BaseController<IThirdPlatformLoginLogService, ThirdPlatformLoginLog> {
    private final IThirdPlatformLoginLogService iThirdPlatformLoginLogService;

    /**
     * @param query 查询对象
     * @Description: 第三方平台登录日志列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(ThirdPlatformLoginLog thirdPlatformLoginLog, BaseQuery query) {
        return this.page(iThirdPlatformLoginLogService, thirdPlatformLoginLog, query);
    }

    /**
     * @param id 对象id
     * @Description: 第三方平台登录日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(@RequestParam("id") String id) {
        return this.getInfoById(iThirdPlatformLoginLogService, id);
    }

    /**
     * @param id thirdPlatformLoginLog id
     * @Description: 删除第三方平台登录日志
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteById(@RequestParam("id") String id) {
        return Result.judge(iThirdPlatformLoginLogService.removeById(id));
    }
}
