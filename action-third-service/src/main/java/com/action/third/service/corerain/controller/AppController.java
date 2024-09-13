package com.action.third.service.corerain.controller;

import com.action.common.core.common.Result;
import com.action.common.corerain.api.service._52.app.AppApi_52;
import com.action.common.corerain.api.struct.dto.*;
import com.action.common.corerain.api.struct.vo.AppInfoVo;
import com.action.common.corerain.api.struct.vo.AppPageVo;
import com.action.common.corerain.api.struct.vo.ResultVo;
import com.action.third.service.corerain.service.SysAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 鲲云平台应用控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@RequestMapping("corerain-app")
@RestController
@RequiredArgsConstructor
public class AppController {
    private final SysAuthService sysAuthService;
    private final AppApi_52 appApi_52;

    /**
     * @param pageSize 每页数量
     * @param current  当前页
     * @param search   查询值 （应用名称/描述）
     * @Description: 查询接口授权列表
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/mc/v1/openapi/apps
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "appsPage", method = RequestMethod.GET)
    public Result<AppPageVo> appsPage(@RequestParam("page_size") Integer pageSize,
                                      @RequestParam("current") Integer current,
                                      @RequestParam(value = "search", required = false) String search) {
        return Result.success(appApi_52.appsPage(pageSize, current, search));
    }

    /**
     * @param appDto 应用创建对象
     * @Description: 创建接口授权
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/mc/v1/openapi/app
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Result create(@RequestBody AppDto appDto) {
        return Result.success(appApi_52.save(appDto));
    }

    /**
     * @param appUpdateDto 应用更新对象
     * @Description: 修改接口授权
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/mc/v1/openapi/app
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result<AppInfoVo> update(@RequestBody AppUpdateDto appUpdateDto) {
        return Result.success(appApi_52.update(appUpdateDto));
    }

    /**
     * @param appDeleteDto 应用删除对象
     * @Description: 删除接口授权
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/mc/v1/openapi/app
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Result delete(@RequestBody AppDeleteDto appDeleteDto) {
        return Result.judge(sysAuthService.deleteApp(appDeleteDto));
    }
}
