package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.common.enums.UseType;
import com.action.common.mybatisplus.extend.base.BaseController;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.entity.SysPost;
import com.action.system.service.ISysPostService;
import com.action.system.service.ISysScopeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 岗位管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("post")
@AllArgsConstructor
public class SysPostController implements BaseController<ISysPostService, SysPost> {
    private final ISysPostService iSysPostService;
    private final ISysScopeService iSysScopeService;

    /**
     * @param query 查询对象
     * @Description: 岗位列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysPost sysPost, BaseQuery query) {
        return this.page(iSysPostService, sysPost, query);
    }

    /**
     * @param sysPost 岗位对象
     * @Description: 保存岗位数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysPost sysPost) {
        SysPost sp = iSysPostService.getOne(this.getLambdaQueryWrapper().eq(SysPost::getPostCode, sysPost.getPostCode()));
        if (Objects.nonNull(sp)) {
            return Result.error("岗位编码已存在");
        }
        boolean isSave = iSysPostService.save(sysPost);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("保存数据失败");
    }

    /**
     * @param sysPost 岗位对象
     * @Description: 更新岗位数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysPost sysPost) {
        SysPost oldPost = iSysPostService.getById(sysPost.getId());
        SysPost sp = iSysPostService.getOne(this.getLambdaQueryWrapper().eq(SysPost::getPostCode, sysPost.getPostCode()));
        if (!oldPost.getPostCode().equals(sysPost.getPostCode()) && Objects.nonNull(sp)) {
            return Result.error("岗位编码已存在");
        }
        boolean isUpdate = iSysPostService.updateById(sysPost);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.error("更新数据失败");
    }

    /**
     * @param ids 岗位id集合
     * @Description: 批量删除岗位数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        List<String> idList = new ArrayList<>();
        List<String> idExistList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            long num = iSysScopeService.postNum(ids.get(i));
            if (num == 0) {
                idList.add(ids.get(i));
            } else {
                idExistList.add(ids.get(i));
            }
        }
        if (idList.size() > 0) {
            iSysPostService.removeBatchByIds(idList);
        }
        if (idExistList.size() > 0) {
            return Result.error("该岗位删除失败，因为包含正被用户使用", idExistList);
        }
        return Result.success("批量通过id删除数据成功");
    }

    /**
     * @param id 岗位id
     * @Description: 禁用岗位
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable String id) {
        SysPost sysPost = iSysPostService.getById(id);
        if (Objects.isNull(sysPost)) {
            return Result.error("该部门不存在");
        }
        sysPost.setStatus(UseType.DISABLED.getStatus());
        iSysPostService.updateById(sysPost);
        iSysScopeService.updatePostStatus(id, UseType.DISABLED.getStatus());
        return Result.success();
    }

    /**
     * @param id 岗位id
     * @Description: 激活岗位
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable String id) {
        SysPost sysPost = iSysPostService.getById(id);
        if (Objects.isNull(sysPost)) {
            return Result.error("该部门不存在");
        }
        sysPost.setStatus(UseType.ENABLE.getStatus());
        iSysPostService.updateById(sysPost);
        iSysScopeService.updatePostStatus(id, UseType.ENABLE.getStatus());
        return Result.success();
    }
}
