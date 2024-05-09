package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseController;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.entity.SysDict;
import com.action.system.entity.SysDictDetail;
import com.action.system.service.ISysDictDetailService;
import com.action.system.service.ISysDictService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 字典管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("dict")
@AllArgsConstructor
public class SysDictController implements BaseController<ISysDictService, SysDict> {
    private final ISysDictService iSysDictService;
    private final ISysDictDetailService iSysDictDetailService;

    /**
     * @param query 查询对象
     * @Description: 字典列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result getDictList(SysDict sysDict, BaseQuery query) {
        return this.page(iSysDictService, sysDict, query);
    }

    /**
     * @param sysDict 字典对象
     * @Description: 保存字典
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysDict sysDict) {
        SysDict sd = iSysDictService.getOne(this.getLambdaQueryWrapper().eq(SysDict::getDictCode, sysDict.getDictCode()));
        if (Objects.nonNull(sd)) {
            return Result.error("字典编码已存在");
        }
        boolean isSave = iSysDictService.save(sysDict);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
    }

    /**
     * @param sysDict 字典对象
     * @Description: 更新字典
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysDict sysDict) {
        SysDict oldDict = iSysDictService.getById(sysDict.getId());
        SysDict sd = iSysDictService.getOne(this.getLambdaQueryWrapper().eq(SysDict::getDictCode, sysDict.getDictCode()));
        if (!oldDict.getDictCode().equals(sysDict.getDictCode()) && Objects.nonNull(sd)) {
            return Result.error("字典编码已存在");
        }
        boolean isUpdate = iSysDictService.updateById(sysDict);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.error("更新数据失败");
    }

    /**
     * @param ids 字典详情id集合
     * @Description: 批量删除字典
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        List<String> idList = new ArrayList<>();
        List<String> idExistList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            long num = iSysDictDetailService.count(this.getLambdaQueryWrapper(new SysDictDetail()).eq(SysDictDetail::getDictId, ids.get(i)));
            if (num == 0) {
                idList.add(ids.get(i));
            } else {
                idExistList.add(ids.get(i));
            }
        }
        if (idList.size() > 0) {
            iSysDictService.removeBatchByIds(idList);
        }
        if (idExistList.size() > 0) {
            return Result.error("该字典删除失败，因为包含正被使用", idExistList);
        }
        return Result.success("批量通过id删除数据成功");
    }
}
