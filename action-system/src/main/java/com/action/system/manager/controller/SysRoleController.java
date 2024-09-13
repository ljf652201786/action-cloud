package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.manager.struct.entity.SysRole;
import com.action.system.bsup.struct.entity.SysUserRole;
import com.action.system.manager.service.ISysRoleService;
import com.action.system.bsup.service.ISysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return Result.success(iSysRoleService.list());
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
        if (iSysRoleService.checkRoleCodeExist(sysRole.getRoleCode())) {
            return Result.failed("角色编码已存在");
        }
        return Result.judge(iSysRoleService.save(sysRole));
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
        return Result.judge(iSysRoleService.updateInfo(sysRole));
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
        return this.deleteByIds(iSysRoleService, ids, (sysRole) -> (iSysUserRoleService.count(this.getLambdaQueryWrapper(new SysUserRole()).eq(SysUserRole::getRoleId, sysRole.getId()))) == 0);
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
        return Result.judge(iSysRoleService.setDefault(id, isDefault));
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
        return Result.judge(iSysRoleService.disable(id));
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
        return Result.judge(iSysRoleService.enable(id));
    }
}
