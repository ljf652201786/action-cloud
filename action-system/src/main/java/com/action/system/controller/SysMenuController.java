package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseController;
import com.action.system.entity.*;
import com.action.system.service.ISysMenuLimitService;
import com.action.system.service.ISysMenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Description: 菜单管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("menu")
@AllArgsConstructor
public class SysMenuController implements BaseController<ISysMenuService, SysMenu> {
    private final ISysMenuService iSysMenuService;
    private final ISysMenuLimitService iSysMenuLimitService;

    /**
     * @Description: 获取菜单详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Result getInfo(String id) {
        return this.getInfoById(iSysMenuService, id);
    }

    /**
     * @param sysMenu 菜单对象
     * @Description: 保存菜单
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysMenu sysMenu) {
        SysMenu sm = iSysMenuService.getOne(this.getLambdaQueryWrapper().eq(SysMenu::getMenuPerm, sysMenu.getMenuPerm()));
        if (Objects.nonNull(sm)) {
            return Result.error("权限标识已使用");
        }
        boolean isSave = iSysMenuService.save(sysMenu);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
    }

    /**
     * @param sysMenu 菜单对象
     * @Description: 更新菜单
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysMenu sysMenu) {
        SysMenu oldMenu = iSysMenuService.getById(sysMenu.getId());
        SysMenu sm = iSysMenuService.getOne(this.getLambdaQueryWrapper().eq(SysMenu::getMenuPerm, sysMenu.getMenuPerm()));
        if (!oldMenu.getMenuPerm().equals(sysMenu.getMenuPerm()) && Objects.nonNull(sm)) {
            return Result.error("权限标识已使用");
        }
        boolean isUpdate = iSysMenuService.updateById(sysMenu);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.error("更新数据失败");
    }

    /**
     * @param id 数据id
     * @Description: 删除菜单
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("id") String id) {
        SysMenuLimit sysMenuLimit = iSysMenuLimitService.getOne(this.getLambdaQueryWrapper(new SysMenuLimit()).eq(SysMenuLimit::getMenuId, id));
        if (Objects.nonNull(sysMenuLimit)) {
            return Result.error("该数据删除失败，因为包含正被使用");
        }
        iSysMenuService.removeById(id);
        return Result.success("通过id删除数据成功");
    }

    /**
     * @Description: 菜单树形选择
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "menuTreeSelect", method = RequestMethod.GET)
    public Result menuTreeSelect() {
        List<SysMenu> treeSelects = iSysMenuService.buildMenuTreeSelect();
        return Result.success("获取数据权限树成功", treeSelects);
    }

    /**
     * @Description: 获取所有菜单权限
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getSysPermission", method = RequestMethod.GET)
    public Result getSysPermission() {
        return Result.success("系统菜单权限获取成功", iSysMenuService.getSysPermission());
    }
}
