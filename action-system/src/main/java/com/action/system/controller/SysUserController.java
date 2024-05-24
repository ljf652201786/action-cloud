package com.action.system.controller;

import com.action.call.vo.AuthUserInfoVo;
import com.action.common.common.RedisSetConstants;
import com.action.common.common.UserSetConstants;
import com.action.common.core.common.Result;
import com.action.common.core.service.RedisCacheServices;
import com.action.common.enums.UseType;
import com.action.common.mybatisplus.extend.base.BaseController;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.dto.SysUserExtend;
import com.action.system.entity.SysUser;
import com.action.system.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Description: 用户管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class SysUserController implements BaseController<ISysUserService, SysUser> {
    private final ISysUserService iSysUserService;
    private final RedisCacheServices redisCacheServices;

    /**
     * @param query 查询 查询参数
     * @Description: 用户列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysUser sysUser, BaseQuery query) {
        return this.page(iSysUserService, sysUser, query);
    }

    /**
     * @param id 用户id
     * @Description: 根据id获取当前用户信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(@RequestParam("id") String id) {
        return this.getInfoById(iSysUserService, id);
    }

    /**
     * @param sysUserExtend 用户扩展对象
     * @Description: 添加用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysUserExtend sysUserExtend) {
        if (StringUtils.isEmpty(sysUserExtend.getUsername()) || StringUtils.isEmpty(sysUserExtend.getEmail()) || StringUtils.isEmpty(sysUserExtend.getPhone())) {
            return Result.failed("缺少必需表单字段");
        }

        if (Objects.nonNull(iSysUserService.findByUsername(sysUserExtend.getUsername()))) {
            return Result.failed("该用户名已被注册");
        }

        if (Objects.nonNull(iSysUserService.findByEmail(sysUserExtend.getEmail()))) {
            return Result.failed("该邮箱已被注册");
        }

        if (Objects.nonNull(iSysUserService.findByPhone(sysUserExtend.getEmail()))) {
            return Result.failed("该手机号已被注册");
        }

        sysUserExtend.setAvatar(sysUserExtend.getAvatar() == null ? UserSetConstants.DEFAULT_AVATAR : sysUserExtend.getAvatar());
        boolean isSave = iSysUserService.save(sysUserExtend);
        if (!isSave) {
            return Result.failed("添加用户失败");
        }
        iSysUserService.saveUserExtendInfo(sysUserExtend);
        return Result.success("添加用户成功");
    }

    /**
     * @param sysUserExtend 用户扩展对象
     * @Description: 修改用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysUserExtend sysUserExtend) {
        SysUser oldUser = iSysUserService.getById(sysUserExtend);
        if (Objects.isNull(oldUser) || oldUser.getUsername().equalsIgnoreCase(sysUserExtend.getUsername())) {
            return Result.failed("该用户不存在");
        }
        if (Objects.nonNull(iSysUserService.findByPhone(sysUserExtend.getPhone())) && !StringUtils.isEmpty(oldUser.getPhone()) && !oldUser.getPhone().equalsIgnoreCase(sysUserExtend.getPhone())) {
            return Result.failed("该手机号已绑定其他账户");
        }
        oldUser.setPhone(sysUserExtend.getPhone());
        if (Objects.nonNull(iSysUserService.findByEmail(sysUserExtend.getEmail())) && !StringUtils.isEmpty(oldUser.getEmail()) && !oldUser.getEmail().equalsIgnoreCase(sysUserExtend.getEmail())) {
            return Result.failed("该邮箱已绑定其他账户");
        }
        oldUser.setEmail(sysUserExtend.getEmail());
        oldUser.setNickName(sysUserExtend.getNickName());
        oldUser.setAvatar(sysUserExtend.getAvatar());
        oldUser.setSex(sysUserExtend.getSex());
        Boolean isUpdate = iSysUserService.updateById(oldUser);
        if (!isUpdate) {
            return Result.failed("用户更新失败");
        }
        iSysUserService.updateUserExtendInfo(oldUser, sysUserExtend);
        return Result.success("用户信息修改成功");
    }

    /**
     * @param sysUser 用户扩展对象
     * @Description: 用户邮箱注册
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
            return Result.failed("缺少必需表单字段");
        }

        if (Objects.nonNull(iSysUserService.findByUsername(sysUser.getUsername()))) {
            return Result.failed("该用户名已被注册");
        }

        if (Objects.nonNull(iSysUserService.findByEmail(sysUser.getEmail()))) {
            return Result.failed("该邮箱已被注册");
        }

        if (Objects.nonNull(iSysUserService.findByPhone(sysUser.getEmail()))) {
            return Result.failed("该手机号已被注册");
        }

        String email_key = RedisSetConstants.AUTH_EMAIL + sysUser.getEmail();
        //获取邮箱验证码
        Object o = redisCacheServices.get(email_key);
        if (Objects.isNull(o)) {
            return Result.failed("验证码失效");
        }
        if (!StringUtils.equalsIgnoreCase(o.toString(), sysUser.getCode())) {
            return Result.failed("验证码错误！");
        }
        Boolean isRegist = iSysUserService.regist(sysUser);
        if (!isRegist) {
            redisCacheServices.remove(email_key);
            return Result.failed("注册失败");
        }
        iSysUserService.setUserDefaultRole(sysUser);
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
    @RequestMapping(value = "editInfo", method = RequestMethod.PUT)
    public Result editInfo(@RequestBody SysUser sysUser) {
        Boolean isEdit = iSysUserService.editInfo(sysUser);
        if (!isEdit) {
            return Result.failed("修改失败");
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
            return Result.failed("该用户不存在");
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
            return Result.failed("该用户不存在");
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
        return Result.failed("修改密码失败");
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
        boolean isReset = iSysUserService.resetPassBatchByIds(ids);
        if (isReset) {
            return Result.success("重置密码成功");
        }
        return Result.failed("重置密码失败");
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
            return Result.failed("缺少必需表单字段");
        }
        String email_key = RedisSetConstants.AUTH_EMAIL + sysUser.getEmail();
        //获取邮箱验证码
        Object o = redisCacheServices.get(email_key);
        if (Objects.isNull(o)) {
            return Result.failed("验证码失效");
        }
        if (!StringUtils.equalsIgnoreCase(o.toString(), sysUser.getCode())) {
            return Result.failed("验证码错误！");
        }

        SysUser user = iSysUserService.getOne(this.getLambdaQueryWrapper().eq(SysUser::getEmail, sysUser.getEmail()));
        if (Objects.isNull(user)) {
            return Result.failed("该邮箱还未注册");
        }
        Boolean isRetrieve = iSysUserService.retrievePass(user);
        if (isRetrieve) {
            return Result.success("找回密码成功", user.getUsername());
        }
        return Result.failed("找回密码失败");
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
        AuthUserInfoVo authUserInfoVo = iSysUserService.findByPhone(phone);
        if (Objects.isNull(authUserInfoVo)) {
            return Result.failed("该手机号未注册");
        }
        return Result.success("通过手机号获取用户信息成功", authUserInfoVo);
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
    public Result getUserByEmail(@RequestParam("email") String email) {
        AuthUserInfoVo authUserInfoVo = iSysUserService.findByEmail(email);
        if (Objects.isNull(authUserInfoVo)) {
            return Result.failed("该手邮箱未注册");
        }
        return Result.success("通过邮箱获取用户信息成功", authUserInfoVo);
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
    public Result getUserNameByWeChatCode(@RequestParam("code") String code) {
        String username = iSysUserService.getUserNameByWeChatCode(code);
        if (StringUtils.isEmpty(username)) {
            return Result.failed("无效扫码");
        }
        return Result.success("扫码获取用户名成功", username);
    }

    /**
     * @param username 用户名
     * @Description: 通过用户名查找用户信息（包含菜单权限和角色）
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getUserByUserName", method = RequestMethod.GET)
    public Result<AuthUserInfoVo> getUserByUserName(@RequestParam("username") String username) {
        AuthUserInfoVo authUserInfoVo = iSysUserService.getUserByUserName(username);
        if (Objects.isNull(authUserInfoVo)) {
            return Result.failed("获取用户信息失败");
        }
        return Result.success("获取用户信息成功", authUserInfoVo);
    }

}
