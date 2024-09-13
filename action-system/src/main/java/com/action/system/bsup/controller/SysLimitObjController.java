package com.action.system.bsup.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.bsup.service.ISysLimitObjService;
import com.action.system.manager.service.ISysRuleService;
import com.action.system.bsup.struct.entity.SysLimitObj;
import com.action.system.manager.struct.entity.SysRule;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 限制对象管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("limitObj")
@RequiredArgsConstructor
public class SysLimitObjController implements BaseController<ISysLimitObjService, SysLimitObj> {
    private final ISysLimitObjService iSysLimitObjService;
    private final ISysRuleService iSysRuleService;

    /**
     * @param query 查询 查询参数
     * @Description: 限制对象列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysLimitObj sysLimitObj, BaseQuery query) {
        return this.page(iSysLimitObjService, sysLimitObj, query);
    }

    /**
     * @Description: 所有限制对象列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "allList", method = RequestMethod.GET)
    public Result allList() {
        return this.getList(iSysLimitObjService, new SysLimitObj());
    }

    /**
     * @param id 限制对象id
     * @Description: 获取限制对象详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Result getInfo(String id) {
        return this.getInfoById(iSysLimitObjService, id);
    }

    /**
     * @param sysLimitObj 限制对象
     * @Description: 保存限制对象
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysLimitObj sysLimitObj) {
        return Result.judge(iSysLimitObjService.save(sysLimitObj));
    }

    /**
     * @param sysLimitObj 限制对象
     * @Description: 更新限制对象
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysLimitObj sysLimitObj) {
        return Result.judge(iSysLimitObjService.updateById(sysLimitObj));
    }

    /**
     * @param ids 限制对象id集合
     * @Description: 批量删除限制对象数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysLimitObjService, ids, (sysLimitObj) -> (iSysRuleService.count(this.getLambdaQueryWrapper(new SysRule()).eq(SysRule::getLimitObjId, sysLimitObj.getId()))) == 0);
    }

}
