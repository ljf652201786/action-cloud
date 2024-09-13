package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;

import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.manager.struct.entity.SysGroup;
import com.action.system.bsup.struct.entity.SysUserGroup;
import com.action.system.manager.service.ISysGroupService;
import com.action.system.bsup.service.ISysUserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 用户组管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/14
 */
@RestController
@RequestMapping("group")
@RequiredArgsConstructor
public class SysGroupController implements BaseController<ISysGroupService, SysGroup> {
    private final ISysGroupService iSysGroupService;
    private final ISysUserGroupService iSysUserGroupService;

    /**
     * @param query 查询对象
     * @Description: 用户组列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysGroup sysGroup, BaseQuery query) {
        return this.page(iSysGroupService, sysGroup, query);
    }

    /**
     * @Description: 获取所有组
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "getAllList", method = RequestMethod.GET)
    public Result getAllList() {
        return Result.success("获取用户组列表成功", iSysGroupService.list());
    }

    /**
     * @param sysGroup 用户组对象
     * @Description: 保存用户组
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysGroup sysGroup) {
        if (iSysGroupService.checkGroupCodeExist(sysGroup.getGroupCode())) {
            return Result.failed("用户组编码已存在");
        }
        return Result.judge(iSysGroupService.save(sysGroup));
    }

    /**
     * @param sysGroup 用户组对象
     * @Description: 更新用户组
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysGroup sysGroup) {
        return Result.judge(iSysGroupService.updateInfo(sysGroup));
    }

    /**
     * @param ids 用户组详情id集合
     * @Description: 批量删除用户组
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysGroupService, ids, (sysGroup) -> (iSysUserGroupService.count(this.getLambdaQueryWrapper(new SysUserGroup()).eq(SysUserGroup::getGroupId, sysGroup.getId()))) == 0);
    }

    /**
     * @param id 用户组id
     * @Description: 禁用用户组
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable("id") String id) {
        return Result.judge(iSysGroupService.disable(id));
    }

    /**
     * @param id 用户组id
     * @Description: 激活用户组
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable("id") String id) {
        return Result.success(iSysGroupService.enable(id));
    }
}
