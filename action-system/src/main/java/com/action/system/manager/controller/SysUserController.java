package com.action.system.manager.controller;

import com.action.call.vo.AuthUserInfoVo;
import com.action.common.biz.base.BaseController;
import com.action.common.common.RedisSetConstants;
import com.action.common.core.common.Result;
import com.action.common.core.service.RedisCacheServices;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.manager.service.ISysUserService;
import com.action.system.manager.struct.dto.SysUserDto;
import com.action.system.manager.struct.entity.SysUser;
import lombok.RequiredArgsConstructor;
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
        return Result.success(iSysUserService.getUserInfoById(id));
    }

    /**
     * @param sysUserDto 用户扩展对象
     * @Description: 添加用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysUserDto sysUserDto) {
        if (StringUtils.isEmpty(sysUserDto.getUsername()) ||
                StringUtils.isEmpty(sysUserDto.getEmail()) ||
                StringUtils.isEmpty(sysUserDto.getPhone())) {
            return Result.failed("缺少必需表单字段");
        }
        if (Objects.nonNull(iSysUserService.findByUsername(sysUserDto.getUsername()))) {
            return Result.failed("该用户名已被注册");
        }
        if (Objects.nonNull(iSysUserService.findByEmail(sysUserDto.getEmail()))) {
            return Result.failed("该邮箱已被注册");
        }
        if (Objects.nonNull(iSysUserService.findByPhone(sysUserDto.getEmail()))) {
            return Result.failed("该手机号已被注册");
        }
        return Result.judge(iSysUserService.saveInfo(sysUserDto));
    }

    /**
     * @param sysUserDto 用户扩展对象
     * @Description: 修改用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysUserDto sysUserDto) {
        if (StringUtils.isEmpty(sysUserDto.getUsername()) ||
                StringUtils.isEmpty(sysUserDto.getEmail()) ||
                StringUtils.isEmpty(sysUserDto.getPhone())) {
            return Result.failed("缺少必需表单字段");
        }
        return Result.judge(iSysUserService.updateInfo(sysUserDto));
    }

    /**
     * @param sysUserDto 用户扩展对象
     * @Description: 用户邮箱注册
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public Result regist(SysUserDto sysUserDto) {
        if (StringUtils.isEmpty(sysUserDto.getUsername()) ||
                StringUtils.isEmpty(sysUserDto.getPassword()) ||
                StringUtils.isEmpty(sysUserDto.getEmail()) ||
                StringUtils.isEmpty(sysUserDto.getPhone()) ||
                StringUtils.isEmpty(sysUserDto.getCode())) {
            return Result.failed("缺少必需表单字段");
        }
        if (Objects.nonNull(iSysUserService.findByUsername(sysUserDto.getUsername()))) {
            return Result.failed("该用户名已被注册");
        }
        if (Objects.nonNull(iSysUserService.findByEmail(sysUserDto.getEmail()))) {
            return Result.failed("该邮箱已被注册");
        }
        if (Objects.nonNull(iSysUserService.findByPhone(sysUserDto.getEmail()))) {
            return Result.failed("该手机号已被注册");
        }
        String email_key = RedisSetConstants.AUTH_EMAIL + sysUserDto.getEmail();
        //获取邮箱验证码
        Object o = redisCacheServices.get(email_key);
        if (Objects.isNull(o)) {
            return Result.failed("验证码失效");
        }
        if (!StringUtils.equalsIgnoreCase(o.toString(), sysUserDto.getCode())) {
            return Result.failed("验证码错误！");
        }
        return Result.judge(iSysUserService.regist(sysUserDto));
    }

    /**
     * @Description: 获取个人信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public Result getUserProfile() {
        return Result.success(iSysUserService.getUserProfile());
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
        return Result.judge(iSysUserService.editInfo(sysUser));
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
    public Result disable(@PathVariable("id") String id) {
        return Result.judge(iSysUserService.disable(id));
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
    public Result enable(@PathVariable("id") String id) {
        return Result.judge(iSysUserService.enable(id));
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
        return Result.judge(iSysUserService.modifyPass(rawPassword, newPassword));
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
        return Result.judge(iSysUserService.resetPassBatchByIds(ids));
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
    public Result retrievePassByEmail(@RequestBody SysUserDto sysUser) {
        if (StringUtils.isBlank(sysUser.getPassword()) ||
                StringUtils.isBlank(sysUser.getEmail()) ||
                StringUtils.isBlank(sysUser.getCode())) {
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
        return Result.judge(iSysUserService.retrievePass(user));
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
     * @param openid openid
     * @Description: 通过openid获取用户信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getUserByOpenId", method = RequestMethod.GET)
    public Result getUserByOpenId(@RequestParam("openid") String openid) {
        AuthUserInfoVo authUserInfoVo = iSysUserService.findByOpenId(openid);
        if (Objects.isNull(authUserInfoVo)) {
            return Result.failed("微信未进行绑定");
        }
        return Result.success("通过openid获取用户信息成功", authUserInfoVo);
    }

    /**
     * @param appid 应用id
     * @Description: 通过应用id获取用户信息
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getUserByAppId", method = RequestMethod.GET)
    public Result getUserByAppId(@RequestParam("appid") String appid) {
        AuthUserInfoVo authUserInfoVo = iSysUserService.findByAppId(appid);
        if (Objects.isNull(authUserInfoVo)) {
            return Result.failed("该应用不存在");
        }
        return Result.success("通过应用id获取用户信息成功", authUserInfoVo);
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
