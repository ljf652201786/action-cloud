package com.action.system.controller;

import com.action.common.common.RedisSetConstants;
import com.action.common.core.common.Result;
import com.action.common.enums.UseType;
import com.action.system.dto.SysUserQuery;
import com.action.system.dto.SysUserExtend;
import com.action.system.entity.SysRole;
import com.action.system.entity.SysUser;
import com.action.system.entity.SysUserRole;
import com.action.system.service.ISysRoleService;
import com.action.system.service.ISysUserRoleService;
import com.action.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @Description: 用户管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("user")
public class SysUserController {
    @Resource
    private ISysUserService iSysUserService;
    @Resource
    private ISysRoleService iSysRoleService;
    @Resource
    private ISysUserRoleService iSysUserRoleService;


    /**
     * @param query 查询 查询参数
     * @Description: 用户列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysUserQuery query) {
        Page<SysUser> rowPage = new Page(query.getPage(), query.getLimit());
        List<SysUser> sysUserList = iSysUserService.list(rowPage, new QueryWrapper<>());
        return Result.success("分页获取系统管理-用户基础信息表列表成功", sysUserList);
    }

    /**
     * @Description: 根据id获取当前用户信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(@RequestParam("id") String id) {
        SysUser sysUser = iSysUserService.getById(id);
        return Result.success("获取用户信息成功", sysUser);
    }

    /**
     * @Description: 添加用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    /*@RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysUserExtend sysUser) {
        List<String> roles = userQueryPara.getRoles();
        List<Scope> scopeList = sysUser.getUserPostList();
        User u = new User();
        BeanUtils.copyProperties(userQueryPara, u);

        if (StringUtils.isBlank(u.getUsername()) || StringUtils.isBlank(u.getPassword())) {
            return Result.error("缺少必需表单字段");
        }
        if (StringUtils.isEmpty(sysUser.getUsername()) || StringUtils.isEmpty(sysUser.getPassword())
                || StringUtils.isEmpty(sysUser.getEmail()) || StringUtils.isEmpty(sysUser.getPhone())) {
            return Result.error("缺少必需表单字段");
        }

        if (Objects.nonNull(iSysUserService.findByUsername(sysUser.getUsername()))) {
            return Result.error("该用户名已被注册");
        }

        if (Objects.nonNull(iSysUserService.findByEmail(sysUser.getEmail()))) {
            return Result.error("该邮箱已被注册");
        }

        if (Objects.nonNull(iSysUserService.findByPhone(sysUser.getEmail()))) {
            return Result.error("该手机号已被注册");
        }

        u.setAvatar(u.getAvatar() == null ? SecurityConstants.Avatar : u.getAvatar());
        boolean b = userService.save(u);
        if (!b) {
            return Result.error("添加失败");
        }
        if (null != roles) {
            //添加角色
            List<UserRole> userRoleList = roles.stream().map(roleId -> {
                UserRole ur = new UserRole();
                ur.setUserId(u.getId());
                ur.setRoleId(roleId);
                ur.setStatus("1");
                return ur;
            }).collect(Collectors.toList());
            iUserRoleService.saveBatch(userRoleList);
        }
        if (null != scopeList) {
            scopeList.stream().forEach(scope -> {
                scope.setUserId(u.getId());
                scope.setUserNick(u.getNickName());
            });
            iScopeService.saveBatch(scopeList);
        }
        return Result.success("添加用户成功");
    }

    *//*修改用户*//*
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public Result edit(@RequestBody UserQueryPara userQueryPara, HttpServletRequest request) {
        List<String> roles = userQueryPara.getRoles();
        List<Scope> scopeList = userQueryPara.getUserPostList();
        User old = userService.getById(userQueryPara.getId());
        //判断新用户名是否存在
        if (!old.getUsername().equals(userQueryPara.getUsername()) && userService.findByUsername(userQueryPara.getUsername()) != null) {
            return Result.error("该用户名已被存在");
        }
        old.setUsername(userQueryPara.getUsername());
        // 若修改了手机和邮箱判断是否唯一
        if (userService.getOne(new QueryWrapper<User>().eq("phone", userQueryPara.getPhone())) != null && old.getPhone() != null && !old.getPhone().equals(userQueryPara.getPhone())) {
            return Result.error("该手机号已绑定其他账户");
        }
        old.setPhone(userQueryPara.getPhone());
        if (!old.getEmail().equals(userQueryPara.getEmail()) && userService.getOne(new QueryWrapper<User>().eq("email", userQueryPara.getEmail())) != null) {
            return Result.error("该邮箱已绑定其他账户");
        }
        old.setEmail(userQueryPara.getEmail());
        old.setNickName(userQueryPara.getNickName());
        old.setAvatar(userQueryPara.getAvatar());
        old.setSex(userQueryPara.getSex());
        old.setType(userQueryPara.getType());
        Boolean bo = userService.updateById(old);
        if (!bo) {
            return Result.error("修改失败");
        }
        //删除该用户角色
        iUserRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", old.getId()));
        if (roles != null) {
            //新角色
            List<UserRole> userRoleList = roles.stream().map(roleId -> {
                UserRole ur = new UserRole();
                ur.setUserId(old.getId());
                ur.setRoleId(roleId);
                ur.setStatus("1");
                return ur;
            }).collect(Collectors.toList());
            iUserRoleService.saveBatch(userRoleList);
        }
        //删除该用户部门岗位
        iScopeService.remove(new QueryWrapper<Scope>().eq("user_id", old.getId()));
        if (scopeList != null) {
            scopeList.stream().forEach(scope -> {
                scope.setUserId(old.getId());
                scope.setUserNick(old.getNickName());
                scope.setStatus("1");
            });
            iScopeService.saveBatch(scopeList);
        }
        //刷新用户权限
        userService.refreshPermissions(old.getUsername(), true);
        return Result.success("修改成功");
    }*/

    /**
     * @param sysUser 用户扩展对象
     * @Description: 用户注册
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public Result regist(SysUserExtend sysUser) {
        if (StringUtils.isEmpty(sysUser.getUsername()) || StringUtils.isEmpty(sysUser.getPassword())
                || StringUtils.isEmpty(sysUser.getEmail()) || StringUtils.isEmpty(sysUser.getPhone())
                || StringUtils.isEmpty(sysUser.getCode())) {
            return Result.error("缺少必需表单字段");
        }

        if (Objects.nonNull(iSysUserService.findByUsername(sysUser.getUsername()))) {
            return Result.error("该用户名已被注册");
        }

        if (Objects.nonNull(iSysUserService.findByEmail(sysUser.getEmail()))) {
            return Result.error("该邮箱已被注册");
        }

        if (Objects.nonNull(iSysUserService.findByPhone(sysUser.getEmail()))) {
            return Result.error("该手机号已被注册");
        }

        String key = RedisSetConstants.EMAIL_KEY + sysUser.getEmail();
        //获取邮箱验证码
        /*String v = redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(v)) {
            return Result.error("验证码已失效");
        }

         if (!StringUtils.equalsIgnoreCase(v, sysUser.getCode())) {
            return Result.error("验证码错误！");
        }*/
        Boolean isRegist = iSysUserService.regist(sysUser);
        if (!isRegist) {
//            redisTemplate.delete(key);
            return Result.error("注册失败");
        }
        List<SysRole> roleList = iSysRoleService.listObjs(new QueryWrapper<SysRole>().eq("default_role", true));
        if (CollectionUtils.isEmpty(roleList)) {
            List<SysUserRole> sysUserRoleList = roleList.stream().map(sysRole -> {
                return new SysUserRole(sysUser.getId(), sysRole.getId(), UseType.ENABLE.getStatus());
            }).collect(Collectors.toList());
            iSysUserRoleService.saveBatch(sysUserRoleList);
        }
        return Result.success("注册成功", sysUser.getId());
    }

    /**
     * @param sysUser 用户
     * @Description: 修改个人信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "editInfo", method = RequestMethod.POST)
    public Result editInfo(@RequestBody SysUser sysUser) {
        Boolean isEdit = iSysUserService.editInfo(sysUser);
        if (!isEdit) {
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }


    /**
     * @param id 用户id
     * @Description: 禁用用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.PUT)
    public Result disable(@PathVariable String id) {
        SysUser sysUser = iSysUserService.getById(id);
        if (Objects.isNull(sysUser)) {
            return Result.error("该用户不存在");
        }
        sysUser.setStatus(UseType.DISABLED.getStatus());
        iSysUserService.updateById(sysUser);
        return Result.success("禁用成功");
    }

    /**
     * @param id 用户id
     * @Description: 激活用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.PUT)
    public Result enable(@PathVariable String id) {
        SysUser sysUser = iSysUserService.getById(id);
        if (Objects.isNull(sysUser)) {
            return Result.error("该用户不存在");
        }
        sysUser.setStatus(UseType.ENABLE.getStatus());
        iSysUserService.updateById(sysUser);
        return Result.success("激活成功");
    }

    /**
     * @param rawPassword 旧密码
     * @param newPassword 新密码
     * @Description: 修改密码
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "modifyPass", method = RequestMethod.PUT)
    public Result modifyPass(@RequestParam String rawPassword, @RequestParam String newPassword) {
        Boolean isModified = iSysUserService.modifyPass(rawPassword, newPassword);
        if (isModified) {
            return Result.success("修改密码成功");
        }
        return Result.error("修改密码失败");
    }

    /**
     * @param ids 用户id集合
     * @Description: 重置密码
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "resetPass", method = RequestMethod.PUT)
    public Result resetPass(@RequestParam("ids") List<String> ids) {
        List<SysUser> UserLists = iSysUserService.getPesetPassOfUserListByIds(ids);
        if (!CollectionUtils.isEmpty(UserLists)) {
            boolean isReset = iSysUserService.updateBatchById(UserLists);
            if (isReset) {
                return Result.success("重置密码成功");
            }
        }
        return Result.error("重置密码失败");
    }

    /**
     * @param sysUser 用户扩展对象
     * @Description: 通过邮箱找回密码
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "retrievePassByEmail", method = RequestMethod.GET)
    public Result retrievePassByEmail(@RequestBody SysUserExtend sysUser) {
        if (StringUtils.isBlank(sysUser.getPassword()) || StringUtils.isBlank(sysUser.getEmail()) || StringUtils.isBlank(sysUser.getCode())) {
            return Result.error("缺少必需表单字段");
        }
        /*String key = RedisConstants.EMAIL_CODE_RETRIEVE_PASSWORD + u.getEmail();
        //获取邮箱验证码
        String v = redisTemplate.opsForValue().get(key);
        if (!StringUtils.equalsIgnoreCase(v, u.getCode())) {
            return Result.error("验证码错误！");
        }
        if (StringUtils.isBlank(v)) {
            return Result.error("验证码已失效！");
        }*/
        SysUser user = iSysUserService.getOne(new QueryWrapper<SysUser>().eq("email", sysUser.getEmail()));
        if (Objects.isNull(user)) {
            return Result.error("该邮箱还未注册");
        }
        Boolean isRetrieve = iSysUserService.retrievePass(user);
        if (isRetrieve) {
            return Result.success("找回密码成功", user.getUsername());
        }
        return Result.error("找回密码失败");
    }


    /**
     * @param phone 手机号
     * @Description: 通过手机号获取用户信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getUserByPhone", method = RequestMethod.GET)
    public Result getUserByPhone(@RequestParam("phone") String phone) {
        SysUser sysUser = iSysUserService.findByPhone(phone);
        if (Objects.isNull(sysUser)) {
            return Result.error("该手机号未注册");
        }
        return Result.success("通过手机号获取用户信息成功", sysUser);
    }

    /**
     * @param email 邮箱
     * @Description: 通过邮箱获取用户信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getUserByEmail", method = RequestMethod.GET)
    public Result getUserByEmail(String email) {
        SysUser sysUser = iSysUserService.findByEmail(email);
        if (Objects.isNull(sysUser)) {
            return Result.error("该手邮箱未注册");
        }
        return Result.success("通过邮箱获取用户信息成功", sysUser);
    }

    /**
     * @param code 微信二维码code
     * @Description: 通过微信二维吗code获取用户名
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getUserNameByWeChatCode", method = RequestMethod.GET)
    public Result getUserNameByWeChatCode(String code) {
        String username = iSysUserService.getUserNameByWeChatCode(code);
        if (StringUtils.isEmpty(username)) {
            return Result.error("无效扫码");
        }
        return Result.success("扫码获取用户名成功", username);
    }


}
