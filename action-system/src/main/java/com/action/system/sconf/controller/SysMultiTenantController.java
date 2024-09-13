package com.action.system.sconf.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.manager.struct.entity.SysUser;
import com.action.system.sconf.service.ISysMultiTenantService;
import com.action.system.sconf.struct.entity.SysMultiTenant;
import com.action.system.manager.service.ISysUserService;
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
        return Result.judge(iSysMultiTenantService.save(sysMultiTenant));
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
        return Result.judge(iSysMultiTenantService.updateById(sysMultiTenant));
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
        return this.deleteById(iSysMultiTenantService, id, tenantId -> !iSysUserService.getOneOpt(this.getLambdaQueryWrapper(new SysUser()).eq(SysUser::getTenantId, tenantId)).isPresent());
    }
}
