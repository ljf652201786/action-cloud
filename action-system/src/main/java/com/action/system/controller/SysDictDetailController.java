package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.system.dto.SysDictDetailQuery;
import com.action.system.entity.SysDept;
import com.action.system.entity.SysDictDetail;
import com.action.system.service.ISysDictDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * @Description: 字典详情管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("dictDetail")
public class SysDictDetailController {
    @Autowired
    private ISysDictDetailService iSysDictDetailService;

    /**
     * @param query 查询对象
     * @Description: 字典详情列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysDictDetailQuery query) {
        Page<SysDictDetail> rowPage = new Page<>(query.getPage(), query.getLimit());
        List<SysDictDetail> sysDictDetailList = iSysDictDetailService.list(rowPage, new QueryWrapper<>());
        return Result.success("获取字典详情列表", sysDictDetailList);
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
        SysDictDetail sdd = iSysDictDetailService.getOne(new QueryWrapper<SysDictDetail>().eq("code", sysDictDetail.getCode()));
        if (Objects.nonNull(sdd)) {
            return Result.error("字典详情编码已存在");
        }
        boolean isSave = iSysDictDetailService.save(sysDictDetail);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
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
        SysDictDetail oldDictDetail = iSysDictDetailService.getById(sysDictDetail.getId());
        SysDictDetail sdd = iSysDictDetailService.getOne(new QueryWrapper<SysDictDetail>().eq("code", sysDictDetail.getCode()));
        if (!oldDictDetail.getCode().equals(sysDictDetail.getCode()) && Objects.nonNull(sdd)) {
            return Result.error("字典详情编码已存在");
        }
        boolean isUpdate = iSysDictDetailService.updateById(sysDictDetail);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.error("更新数据失败");
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
        boolean isRemove = iSysDictDetailService.removeBatchByIds(ids);
        if (isRemove) {
            return Result.success("删除数据成功");
        }
        return Result.error("删除数据失败");
    }
}
