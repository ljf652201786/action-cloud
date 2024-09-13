package com.action.system.bsup.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.bsup.struct.entity.SysDictDetail;
import com.action.system.bsup.service.ISysDictDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 字典详情管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("dictDetail")
@RequiredArgsConstructor
public class SysDictDetailController implements BaseController<ISysDictDetailService, SysDictDetail> {
    private final ISysDictDetailService iSysDictDetailService;

    /**
     * @param query 查询对象
     * @Description: 字典详情列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysDictDetail sysDictDetail, BaseQuery query) {
        return this.page(iSysDictDetailService, sysDictDetail, query);
    }

    /**
     * @param code 字典code
     * @Description: 字典详情列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "selection", method = RequestMethod.GET)
    public Result selection(@RequestParam("code") String code) {
        return Result.success(iSysDictDetailService.getDetailByCode(code));
    }

    /**
     * @param sysDictDetail 字典详情对象
     * @Description: 保存字典详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysDictDetail sysDictDetail) {
        return Result.judge(iSysDictDetailService.save(sysDictDetail));
    }

    /**
     * @param sysDictDetail 字典详情对象
     * @Description: 更新字典详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysDictDetail sysDictDetail) {
        return Result.judge(iSysDictDetailService.updateById(sysDictDetail));
    }

    /**
     * @param ids 字典详情id集合
     * @Description: 批量删除字典详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysDictDetailService, ids);
    }
}
