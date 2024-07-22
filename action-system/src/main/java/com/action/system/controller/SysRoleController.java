package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.struct.entity.SysRole;
import com.action.system.struct.entity.SysUserRole;
import com.action.system.service.ISysRoleService;
import com.action.system.service.ISysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 角色管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class SysRoleController implements BaseController<ISysRoleService, SysRole> {
    private final ISysRoleService iSysRoleService;
    private final ISysUserRoleService iSysUserRoleService;

    /**
     * @param query 查询对象
     * @Description: 角色列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysRole sysRole, BaseQuery query) {
        return this.page(iSysRoleService, sysRole, query);
    }

    /**
     * @param id 角色id
     * @Description: 根据id获取角色信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(@RequestParam("id") String id) {
        return this.getInfoById(iSysRoleService, id);
    }

    /**
     * @Description: 获取全部角色数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getAllList", method = RequestMethod.GET)
    public Result getAllList() {
        List<SysRole> result = iSysRoleService.list();
        return Result.success("获取角色列表成功", result);
    }

    /**
     * @param sysRole 角色对象
     * @Description: 保存角色数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysRole sysRole) {
        SysRole sr = iSysRoleService.getOne(this.getLambdaQueryWrapper().eq(SysRole::getRoleCode, sysRole.getRoleCode()));
        if (Objects.nonNull(sr)) {
            return Result.failed("角色编码已存在");
        }
        boolean isSave = iSysRoleService.save(sysRole);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
    }

    /**
     * @param sysRole 角色对象
     * @Description: 保存角色数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysRole sysRole) {
        SysRole oldRole = iSysRoleService.getById(sysRole.getId());
        SysRole sr = iSysRoleService.getOne(this.getLambdaQueryWrapper().eq(SysRole::getRoleCode, sysRole.getRoleCode()));
        if (!oldRole.getRoleCode().equals(sysRole.getRoleCode()) && Objects.nonNull(sr)) {
            return Result.failed("角色编码已存在");
        }
        boolean isUpdate = iSysRoleService.updateById(sysRole);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
    }

    /**
     * @param ids 角色id集合
     * @Description: 批量删除角色数据
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
            long num = iSysUserRoleService.count(this.getLambdaQueryWrapper(new SysUserRole()).eq(SysUserRole::getRoleId, ids.get(i)));
            if (num == 0) {
                idList.add(ids.get(i));
            } else {
                idExistList.add(ids.get(i));
            }
        }
        if (idList.size() > 0) {
            iSysRoleService.removeBatchByIds(idList);
        }
        if (idExistList.size() > 0) {
            return Result.failed("该角色删除失败，因为包含正被用户使用", idExistList);
        }
        return Result.success("批量通过id删除数据成功");
    }

    /**
     * @param id        角色id
     * @param isDefault 是否默认
     * @Description: 设置默认角色
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "setDefault", method = RequestMethod.PUT)
    public Result setDefault(@RequestParam String id, @RequestParam boolean isDefault) {
        SysRole sysRole = iSysRoleService.getById(id);
        if (Objects.isNull(sysRole)) {
            return Result.failed("角色不存在");
        }
        sysRole.setDefaultRole(isDefault);
        iSysRoleService.updateById(sysRole);
        return Result.success("设置成功");
    }

    /**
     * @param id 角色id
     * @Description: 禁用角色
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable("id") String id) {
        SysRole sysRole = iSysRoleService.getById(id);
        if (Objects.isNull(sysRole)) {
            return Result.failed("该角色不存在");
        }
        sysRole.setStatus(StatusType.DISABLED.getStatus());
        iSysRoleService.updateById(sysRole);
        iSysUserRoleService.updateRoleStatus(id, StatusType.DISABLED.getStatus());
        return Result.success("禁用成功");
    }

    /**
     * @param id 角色id
     * @Description: 激活角色
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable("id") String id) {
        SysRole sysRole = iSysRoleService.getById(id);
        if (Objects.isNull(sysRole)) {
            return Result.failed("该角色不存在");
        }
        sysRole.setStatus(StatusType.ENABLE.getStatus());
        iSysRoleService.updateById(sysRole);
        iSysUserRoleService.updateRoleStatus(id, StatusType.ENABLE.getStatus());
        return Result.success("激活成功");
    }
}
