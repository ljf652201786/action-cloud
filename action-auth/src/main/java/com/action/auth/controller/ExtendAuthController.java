package com.action.auth.controller;

import com.action.common.auth.base.BaseAuthController;
import com.action.common.auth.token.WeChatAuthenticationToken;
import com.action.common.auth.user.SecurityUser;
import com.action.common.auth.util.SecurityUtil;
import com.action.common.common.UserSetConstants;
import com.action.common.core.common.Result;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 扩展认证方式控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/3
 */
@RestController
@RequestMapping("auth/extend")
public class ExtendAuthController extends BaseAuthController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SecurityUtil securityUtil;

    /**
     * @param code 验证码
     * @Description: 其他登录认证方式
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "otherAuth", method = RequestMethod.POST)
    public Result otherAuth(@RequestParam("code") String code) {
        String username = "";
        WeChatAuthenticationToken weChatAuthenticationToken = new WeChatAuthenticationToken(username);
        try {
            Authentication authenticate = authenticationManager.authenticate(weChatAuthenticationToken);
            String token = this.getAuthToken(authenticate);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("token", token);
            return Result.success("Login successful", resultMap);
        } catch (Exception e) {
            return Result.error("Login failed");
        }
    }

    /**
     * @param password 明文密码
     * @Description: 生成密码
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "generatePwd", method = RequestMethod.GET)
    public Result generatePwd(@RequestParam("password") String password) {
        if (StringUtils.isEmpty(password)) {
            return Result.error("密码不能为空，返回原密码", password);
        }
        String encodePwd = passwordEncoder.encode(password);
        return Result.success("加密密码已生成", encodePwd);
    }

    /**
     * @Description: 获取默认加密密码
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getDefaultPwd", method = RequestMethod.GET)
    public Result getDefaultPwd() {
        String encodePwd = passwordEncoder.encode(UserSetConstants.DEFAULT_PASSWORD);
        return Result.success("获取默认密码成功", encodePwd);
    }

    /**
     * @Description: 获取当前用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getCurrentUser", method = RequestMethod.GET)
    public Result getCurrentUser() {
        SecurityUser securityUser = securityUtil.getSecurityUser();
        if (Objects.isNull(securityUser)) {
            return Result.error("未知用户");
        }
        return Result.success("获取当前用户成功", securityUser);
    }

    /**
     * @Description: 密码验证
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "pwdMatches", method = RequestMethod.GET)
    public Result pwdMatches(@RequestParam("rawPassword") String rawPassword) {
        String encodedPassword = securityUtil.getPassword();
        if (StringUtils.isEmpty(encodedPassword)) {
            return Result.error("未知用户");
        }
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        if (matches) {
            return Result.success("密码验证成功", securityUtil.getUserId());
        }
        return Result.error("密码验证失败", matches);
    }
}
