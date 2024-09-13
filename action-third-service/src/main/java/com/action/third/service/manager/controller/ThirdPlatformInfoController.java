package com.action.third.service.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.third.service.manager.service.IThirdPlatformInfoService;
import com.action.third.service.manager.struct.entity.ThirdPlatformInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 第三方平台信息控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@RequestMapping("info")
@RestController
@RequiredArgsConstructor
public class ThirdPlatformInfoController implements BaseController<IThirdPlatformInfoService, ThirdPlatformInfo> {
    private final IThirdPlatformInfoService iThirdPlatformInfoService;

    /**
     * @param query 查询对象
     * @Description: 第三方平台信息列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(ThirdPlatformInfo thirdPlatformInfo, BaseQuery query) {
        return this.page(iThirdPlatformInfoService, thirdPlatformInfo, query);
    }

    /**
     * @param id 对象id
     * @Description: 第三方平台信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(@RequestParam("id") String id) {
        return this.getInfoById(iThirdPlatformInfoService, id);
    }

    /**
     * @param thirdPlatformInfo 第三方平台信息对象
     * @Description: 保存第三方平台信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
//    @PreAuthorize("@ss.hasPerm('sys:test:save')")   // 开启注解权限时生效
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody ThirdPlatformInfo thirdPlatformInfo) {
        return Result.judge(iThirdPlatformInfoService.save(thirdPlatformInfo));
    }

    /**
     * @param thirdPlatformInfo 第三方平台信息对象
     * @Description: 更新第三方平台信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody ThirdPlatformInfo thirdPlatformInfo) {
        return Result.judge(iThirdPlatformInfoService.updateById(thirdPlatformInfo));
    }

    /**
     * @param id thirdPlatformInfo id
     * @Description: 删除test
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteById(@RequestParam("id") String id) {
        return Result.judge(iThirdPlatformInfoService.removeById(id));
    }
}
