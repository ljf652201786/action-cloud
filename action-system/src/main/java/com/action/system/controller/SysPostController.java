package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.struct.entity.SysPost;
import com.action.system.service.ISysPostService;
import com.action.system.service.ISysScopeService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
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
     * @Description: 获取所有岗位
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "getAllList", method = RequestMethod.GET)
    public Result getAllList(SysPost sysPost) {
        sysPost.setStatus(StatusType.ENABLE.getStatus());
        List<SysPost> list = iSysPostService.getSelectList(sysPost);
        return Result.success("获取岗位列表成功", list);
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
            return Result.failed("岗位编码已存在");
        }
        boolean isSave = iSysPostService.save(sysPost);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("保存数据失败");
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
            return Result.failed("岗位编码已存在");
        }
        boolean isUpdate = iSysPostService.updateById(sysPost);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
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
        return this.deleteByIds(iSysPostService, ids, (id) -> (iSysScopeService.postNum(id)) == 0);
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
    public Result disable(@PathVariable("id") String id) {
        SysPost sysPost = iSysPostService.getById(id);
        if (Objects.isNull(sysPost)) {
            return Result.failed("该部门不存在");
        }
        sysPost.setStatus(StatusType.DISABLED.getStatus());
        iSysPostService.updateById(sysPost);
        iSysScopeService.updatePostStatus(id, StatusType.DISABLED.getStatus());
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
    public Result enable(@PathVariable("id") String id) {
        SysPost sysPost = iSysPostService.getById(id);
        if (Objects.isNull(sysPost)) {
            return Result.failed("该部门不存在");
        }
        sysPost.setStatus(StatusType.ENABLE.getStatus());
        iSysPostService.updateById(sysPost);
        iSysScopeService.updatePostStatus(id, StatusType.ENABLE.getStatus());
        return Result.success();
    }
}
