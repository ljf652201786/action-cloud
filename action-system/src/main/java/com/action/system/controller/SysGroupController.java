package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.struct.entity.SysGroup;
import com.action.system.struct.entity.SysUserGroup;
import com.action.system.service.ISysGroupService;
import com.action.system.service.ISysUserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Result getDictList(SysGroup sysGroup, BaseQuery query) {
        return this.page(iSysGroupService, sysGroup, query);
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
        SysGroup sg = iSysGroupService.getOne(this.getLambdaQueryWrapper().eq(SysGroup::getGroupCode, sysGroup.getGroupCode()));
        if (Objects.nonNull(sg)) {
            return Result.failed("用户组编码已存在");
        }
        boolean isSave = iSysGroupService.save(sysGroup);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
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
        SysGroup oldGroup = iSysGroupService.getById(sysGroup.getId());
        SysGroup sg = iSysGroupService.getOne(this.getLambdaQueryWrapper().eq(SysGroup::getGroupCode, sysGroup.getGroupCode()));
        if (!oldGroup.getGroupCode().equals(sysGroup.getGroupCode()) && Objects.nonNull(sg)) {
            return Result.failed("字典编码已存在");
        }
        boolean isUpdate = iSysGroupService.updateById(sysGroup);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
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
        List<String> idList = new ArrayList<>();
        List<String> idExistList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            long num = iSysUserGroupService.count(this.getLambdaQueryWrapper(new SysUserGroup()).eq(SysUserGroup::getGroupId, ids.get(i)));
            if (num == 0) {
                idList.add(ids.get(i));
            } else {
                idExistList.add(ids.get(i));
            }
        }
        if (idList.size() > 0) {
            iSysGroupService.removeBatchByIds(idList);
        }
        if (idExistList.size() > 0) {
            return Result.failed("该用户组删除失败，因为包含正被使用", idExistList);
        }
        return Result.success("批量通过id删除数据成功");
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
        SysGroup sysGroup = iSysGroupService.getById(id);
        if (Objects.isNull(sysGroup)) {
            return Result.failed("该用户组不存在");
        }
        sysGroup.setStatus(StatusType.DISABLED.getStatus());
        iSysGroupService.updateById(sysGroup);
        iSysUserGroupService.updateGroupStatus(id, StatusType.DISABLED.getStatus());
        return Result.success();
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
        SysGroup sysGroup = iSysGroupService.getById(id);
        if (Objects.isNull(sysGroup)) {
            return Result.failed("该用户组不存在");
        }
        sysGroup.setStatus(StatusType.ENABLE.getStatus());
        iSysGroupService.updateById(sysGroup);
        iSysUserGroupService.updateGroupStatus(id, StatusType.ENABLE.getStatus());
        return Result.success();
    }
}
