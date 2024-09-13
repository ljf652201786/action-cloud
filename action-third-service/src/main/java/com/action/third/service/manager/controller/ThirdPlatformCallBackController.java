package com.action.third.service.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.third.service.manager.service.IThirdPlatformCallBackService;
import com.action.third.service.manager.struct.entity.ThirdPlatformCallBack;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 第三方平台回调信息控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@RequestMapping("callback")
@RestController
@RequiredArgsConstructor
public class ThirdPlatformCallBackController implements BaseController<IThirdPlatformCallBackService, ThirdPlatformCallBack> {
    private final IThirdPlatformCallBackService iThirdPlatformCallBackService;

    /**
     * @param query 查询对象
     * @Description: 第三方平台回调信息列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(ThirdPlatformCallBack thirdPlatformCallBack, BaseQuery query) {
        return this.page(iThirdPlatformCallBackService, thirdPlatformCallBack, query);
    }

    /**
     * @param id 对象id
     * @Description: 第三方平台回调信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(@RequestParam("id") String id) {
        return this.getInfoById(iThirdPlatformCallBackService, id);
    }

    /**
     * @param jsonObject 第三方平台回调信息对象
     * @Description: 保存第三方平台回调信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
//    @PreAuthorize("@ss.hasPerm('sys:test:save')")   // 开启注解权限时生效
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody JSONObject jsonObject) {
        return Result.judge(iThirdPlatformCallBackService.analyzeAndSave(jsonObject));
    }

    /**
     * @param id id
     * @Description: 删除第三方平台回调信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteById(@RequestParam("id") String id) {
        return Result.judge(iThirdPlatformCallBackService.removeById(id));
    }
}
