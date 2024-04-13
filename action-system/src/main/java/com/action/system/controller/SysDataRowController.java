package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.common.enums.UseType;
import com.action.system.entity.SysRowLimit;
import com.action.system.service.ISysRowLimitService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * @Description: 数据行权限管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("dataRow")
public class SysDataRowController {
    @Resource
    private ISysRowLimitService iSysRowLimitService;

    /**
     * @param sysRowLimit 数据行对象
     * @Description: 保存数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysRowLimit sysRowLimit) {
        if (StringUtils.isEmpty(sysRowLimit.getDataId()) || StringUtils.isEmpty(sysRowLimit.getContactId()) || StringUtils.isEmpty(sysRowLimit.getType())) {
            return Result.error("缺少必要数据");
        }
        sysRowLimit.setStatus(UseType.ENABLE.getStatus());
        sysRowLimit.setCondition("And");
        boolean isSave = iSysRowLimitService.save(sysRowLimit);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.error("数据保存失败");
    }

    /**
     * @param sysRowLimit 数据行对象
     * @Description: 更新数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysRowLimit sysRowLimit) {
        if (StringUtils.isEmpty(sysRowLimit.getDataId()) || StringUtils.isEmpty(sysRowLimit.getContactId()) || StringUtils.isEmpty(sysRowLimit.getType())) {
            return Result.error("缺少必要数据");
        }
        sysRowLimit.setStatus(UseType.ENABLE.getStatus());
        sysRowLimit.setCondition("And");
        boolean isUpdate = iSysRowLimitService.updateById(sysRowLimit);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.error("更新数据失败");
    }

    /**
     * @param id 数据行id
     * @Description: 删除数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("id") String id) {
        iSysRowLimitService.removeById(id);
        return Result.success("通过id删除数据成功");
    }

    /**
     * @param id 数据行id
     * @Description: 禁用数据行限制
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable String id) {
        SysRowLimit sysRowLimit = iSysRowLimitService.getById(id);
        if (Objects.isNull(sysRowLimit)) {
            return Result.error("该数据不存在");
        }
        sysRowLimit.setStatus(UseType.DISABLED.getStatus());
        iSysRowLimitService.updateById(sysRowLimit);
        //刷新缓存权限
        /*List<Scope> userIdScopeList = iScopeService.list(new QueryWrapper<Scope>().select("user_id").eq("dept_id", deptId));
        String userIdsVar = userIdScopeList.stream().map(sp -> sp.getUserId()).collect(Collectors.joining(","));
        List<String> usernames = userService.list(new QueryWrapper<User>().in("id", userIdsVar)).stream().map(user -> user.getUsername()).collect(Collectors.toList());
        userService.refreshPermissions(usernames, false);*/
        return Result.success();
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
    public Result enable(@PathVariable String id) {
        SysRowLimit sysRowLimit = iSysRowLimitService.getById(id);
        if (Objects.isNull(sysRowLimit)) {
            return Result.error("该数据不存在");
        }
        sysRowLimit.setStatus(UseType.ENABLE.getStatus());
        iSysRowLimitService.updateById(sysRowLimit);
        //刷新缓存权限
        /*List<Scope> userIdScopeList = iScopeService.list(new QueryWrapper<Scope>().select("user_id").eq("dept_id", deptId));
        String userIdsVar = userIdScopeList.stream().map(sp -> sp.getUserId()).collect(Collectors.joining(","));
        List<String> usernames = userService.list(new QueryWrapper<User>().in("id", userIdsVar)).stream().map(user -> user.getUsername()).collect(Collectors.toList());
        userService.refreshPermissions(usernames, false);*/
        return Result.success();
    }
}
