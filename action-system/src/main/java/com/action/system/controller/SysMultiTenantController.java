package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.service.ISysMultiTenantService;
import com.action.system.service.ISysUserService;
import com.action.system.struct.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: 多租户管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("tenant")
@RequiredArgsConstructor
public class SysMultiTenantController implements BaseController<ISysMultiTenantService, SysMultiTenant> {
    private final ISysMultiTenantService iSysMultiTenantService;
    private final ISysUserService iSysUserService;

    /**
     * @param query 查询对象
     * @Description: 租户列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysMultiTenant sysMultiTenant, BaseQuery query) {
        return this.page(iSysMultiTenantService, sysMultiTenant, query);
    }

    /**
     * @param sysMultiTenant 租户对象
     * @Description: 保存租户详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysMultiTenant sysMultiTenant) {
        boolean isSave = iSysMultiTenantService.save(sysMultiTenant);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
    }

    /**
     * @param sysMultiTenant 租户对象
     * @Description: 更新租户详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysMultiTenant sysMultiTenant) {
        boolean isUpdate = iSysMultiTenantService.updateById(sysMultiTenant);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
    }

    /**
     * @param id 租户id集合
     * @Description: 删除租户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteById(@RequestParam("id") String id) {
        return this.deleteById(iSysMultiTenantService, id, tenantId -> null == iSysUserService.getOne(this.getLambdaQueryWrapper(new SysUser()).eq(SysUser::getTenantId, tenantId)));
    }
}
