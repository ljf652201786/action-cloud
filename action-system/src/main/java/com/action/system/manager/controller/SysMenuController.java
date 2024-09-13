package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.system.bsup.service.ISysMenuLimitService;
import com.action.system.manager.service.ISysMenuService;
import com.action.system.manager.struct.entity.SysMenu;
import com.action.system.bsup.struct.entity.SysMenuLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 菜单管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class SysMenuController implements BaseController<ISysMenuService, SysMenu> {
    private final ISysMenuService iSysMenuService;
    private final ISysMenuLimitService iSysMenuLimitService;

    @RequestMapping(value = "routes", method = RequestMethod.GET)
    public Result listRoutes() {
        return Result.success(iSysMenuService.listRoutes());
    }

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
        return Result.judge(iSysMenuService.save(sysMenu));
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
        return Result.judge(iSysMenuService.updateById(sysMenu));
    }

    /**
     * @param ids 数据ids
     * @Description: 删除菜单
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysMenuService, ids, (sysMenu -> iSysMenuLimitService.getOneOpt(this.getLambdaQueryWrapper(new SysMenuLimit()).eq(SysMenuLimit::getMenuId, sysMenu.getId())).isPresent()));
    }

    /**
     * @Description: 菜单树形选择
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "menuTreeSelect", method = RequestMethod.GET)
    public Result menuTreeSelect(SysMenu sysMenu) {
        return this.treeSelect(iSysMenuService, sysMenu, SysMenu::getId);
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
