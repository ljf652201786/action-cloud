package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.manager.struct.entity.SysPost;
import com.action.system.manager.service.ISysPostService;
import com.action.system.bsup.service.ISysScopeService;
import com.action.system.manager.struct.vo.PostVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return this.page(iSysPostService, sysPost, query, PostVo.class);
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
        return Result.success(iSysPostService.getSelectList(sysPost));
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
        if (iSysPostService.checkPostCodeExist(sysPost.getPostCode())) {
            return Result.failed("岗位编码已存在");
        }
        return Result.judge(iSysPostService.save(sysPost));
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
        return Result.judge(iSysPostService.updateInfo(sysPost));
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
        return this.deleteByIds(iSysPostService, ids, (sysPost) -> (iSysScopeService.postNum(sysPost.getId())) == 0);
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
        return Result.judge(iSysPostService.disable(id));
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
        return Result.judge(iSysPostService.disable(id));
    }
}
