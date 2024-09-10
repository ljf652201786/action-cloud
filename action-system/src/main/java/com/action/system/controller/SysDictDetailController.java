package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.service.ISysDictService;
import com.action.system.struct.entity.SysDictDetail;
import com.action.system.service.ISysDictDetailService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
        List<SysDictDetail> list = iSysDictDetailService.list(this.getLambdaQueryWrapper()
                .eq(StringUtils.isNotEmpty(code), SysDictDetail::getCode, code)
                .eq(SysDictDetail::getStatus, StatusType.ENABLE.getStatus())
                .orderByAsc(SysDictDetail::getSort));
        return Result.success(list);
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
        boolean isSave = iSysDictDetailService.save(sysDictDetail);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
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
        boolean isUpdate = iSysDictDetailService.updateById(sysDictDetail);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
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
