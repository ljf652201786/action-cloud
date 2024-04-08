package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.common.enums.UseType;
import com.action.system.dto.SysRoleQuery;
import com.action.system.entity.SysRole;
import com.action.system.entity.SysUserRole;
import com.action.system.service.ISysRoleService;
import com.action.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
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
public class SysRoleController {
    @Resource
    private ISysRoleService iSysRoleService;
    @Resource
    private ISysUserRoleService iSysUserRoleService;

    /**
     * @param query 查询对象
     * @Description: 角色列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysRoleQuery query) {
        Page<SysRole> rowPage = new Page(query.getPage(), query.getLimit());
        List<SysRole> sysRoleList = iSysRoleService.list(rowPage, new QueryWrapper<>());
        return Result.success("分页获取系统管理-角色基础信息表列表成功", sysRoleList);
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
        SysRole sysRole = iSysRoleService.getById(id);
        return Result.success("获取角色信息成功", sysRole);
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
        return Result.success("获取系统管理-角色表 列表成功", result);
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
        SysRole sr = iSysRoleService.getOne(new QueryWrapper<SysRole>().eq("code", sysRole.getCode()));
        if (Objects.nonNull(sr)) {
            return Result.error("角色编码已存在");
        }
        boolean bo = iSysRoleService.save(sysRole);
        return Result.success("保存数据成功", bo);
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
        SysRole sr = iSysRoleService.getOne(new QueryWrapper<SysRole>().eq("code", sysRole.getCode()));
        if (!oldRole.getCode().equals(sysRole.getCode()) && Objects.nonNull(sr)) {
            return Result.error("角色编码已存在");
        }
        boolean bo = iSysRoleService.updateById(sysRole);
        return Result.success("更新数据成功", bo);
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
            long num = iSysUserRoleService.count(new QueryWrapper<SysUserRole>().eq("role_id", ids.get(i)));
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
            return Result.error("该角色删除失败，因为包含正被用户使用", idExistList);
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
            return Result.error("角色不存在");
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
    public Result disable(@PathVariable String id) {
        SysRole sysRole = iSysRoleService.getById(id);
        if (Objects.isNull(sysRole)) {
            return Result.error("该角色不存在");
        }
        sysRole.setStatus(UseType.DISABLED.getStatus());
        iSysRoleService.updateById(sysRole);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setStatus(UseType.DISABLED.getStatus());
        iSysUserRoleService.update(sysUserRole, new UpdateWrapper<SysUserRole>().eq("role_id", id));
        //刷新缓存权限
        /*List<UserRole> userIdScopeList = userRoleService.list(new QueryWrapper<UserRole>().select("user_id").eq("role_id", roleId));
        String userIdsVar = userIdScopeList.stream().map(sp -> sp.getUserId()).collect(Collectors.joining(","));
        List<String> usernames = userService.list(new QueryWrapper<User>().in("id", userIdsVar)).stream().map(user -> user.getUsername()).collect(Collectors.toList());
        userService.refreshPermissions(usernames, false);*/
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
    public Result enable(@PathVariable String id) {
        SysRole sysRole = iSysRoleService.getById(id);
        if (Objects.isNull(sysRole)) {
            return Result.error("该角色不存在");
        }
        sysRole.setStatus(UseType.ENABLE.getStatus());
        iSysRoleService.updateById(sysRole);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setStatus(UseType.ENABLE.getStatus());
        iSysUserRoleService.update(sysUserRole, new UpdateWrapper<SysUserRole>().eq("role_id", id));
        //刷新缓存权限
        /*List<UserRole> userIdScopeList = userRoleService.list(new QueryWrapper<UserRole>().select("user_id").eq("role_id", roleId));
        String userIdsVar = userIdScopeList.stream().map(sp -> sp.getUserId()).collect(Collectors.joining(","));
        List<String> usernames = userService.list(new QueryWrapper<User>().in("id", userIdsVar)).stream().map(user -> user.getUsername()).collect(Collectors.toList());
        userService.refreshPermissions(usernames, false);*/
        return Result.success("激活成功");
    }
}
