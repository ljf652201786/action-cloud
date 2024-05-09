/*
package com.action.auth.controller;

import com.action.call.clients.RemoteSystemClients;
import com.action.call.vo.LogLoginVo;
import com.action.common.auth.base.BaseAuthController;
import com.action.common.auth.user.SecurityUser;
import com.action.common.auth.util.SecurityUtil;
import com.action.common.common.UserSetConstants;
import com.action.common.core.common.Result;
import com.action.common.core.tool.IpUtils;
import com.action.common.core.tool.UserAgentUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

*/
/**
 * @Description: 扩展认证方式控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/4/3
 *//*

@RestController
@RequestMapping("auth/extend")
public class ExtendAuthController extends BaseAuthController {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SecurityUtil securityUtil;
    @Resource
    private RemoteSystemClients remoteSystemClients;

    */
/**
     * @param code 验证码
     * @Description: 其他登录认证方式
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     *//*

    */
/*@RequestMapping(value = "otherAuth", method = RequestMethod.POST)
    public Result otherAuth(@RequestParam("code") String code) {
        String username = "";
        WeChatAuthenticationToken weChatAuthenticationToken = new WeChatAuthenticationToken(username);
        Map<String, Object> authTokenMap = this.authentication(username, weChatAuthenticationToken);
        if (CollectionUtils.isEmpty(authTokenMap)) {
            return Result.error("Login failed");
        }
        return Result.success("Login successful", authTokenMap);
    }*//*


    */
/**
     * @param securityUser 验证用户对象
     * @param resultMap    返回值封装对象
     * @param request      请求对象
     * @param code         登录状态
     * @Description: 处理登录后的操作逻辑
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     *//*

    public boolean after(SecurityUser securityUser, Map<String, Object> resultMap, HttpServletRequest request, int code) {
        LogLoginVo logLoginVo = new LogLoginVo();
        String ipAdrress = IpUtils.getIpAdrress(request);
        logLoginVo.setUsername(securityUser.getUsername());
        logLoginVo.setIpAddress(ipAdrress);
        logLoginVo.setStatus(String.valueOf(code));
        logLoginVo.setMsg(HttpServletResponse.SC_OK == code ? "登录成功" : "登录失败");
        logLoginVo.setRequestTime(new Date());
        UserAgentUtils.AgentDo agentDo = UserAgentUtils.getAgentDo(request.getHeader("User-Agent"));
        UserAgentUtils.BrowserDo browserDo = agentDo.getBrowserDo();
        UserAgentUtils.OsDo osDo = agentDo.getOsDo();
        logLoginVo.setBrowserName(browserDo.getBrowserName());
        logLoginVo.setBrowserType(browserDo.getBrowserType());
        logLoginVo.setBrowserGroup(browserDo.getBrowserGroup());
        logLoginVo.setBrowserManufacturer(browserDo.getBrowserManufacturer());
        logLoginVo.setBrowserRenderingengine(browserDo.getBrowserRenderingEngine());
        logLoginVo.setBrowserVersion(browserDo.getBrowserVersion());
        logLoginVo.setOsName(osDo.getOsName());
        logLoginVo.setOsType(osDo.getOsType());
        logLoginVo.setOsGroup(osDo.getOsGroup());
        logLoginVo.setOsManufacturer(osDo.getOsManufacturer());
        Result result = remoteSystemClients.save(logLoginVo);
        if (result.get("code").equals(HttpServletResponse.SC_OK)) {
            return true;
        }
        return false;
    }

    */
/**
     * @param password 明文密码
     * @Description: 生成密码
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     *//*

    @RequestMapping(value = "generatePwd", method = RequestMethod.GET)
    public Result generatePwd(@RequestParam("password") String password) {
        if (StringUtils.isEmpty(password)) {
            return Result.error("密码不能为空，返回原密码", password);
        }
        String encodePwd = passwordEncoder.encode(password);
        return Result.success("加密密码已生成", encodePwd);
    }

    */
/**
     * @Description: 获取默认加密密码
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     *//*

    @RequestMapping(value = "getDefaultPwd", method = RequestMethod.GET)
    public Result getDefaultPwd() {
        String encodePwd = passwordEncoder.encode(UserSetConstants.DEFAULT_PASSWORD);
        return Result.success("获取默认密码成功", encodePwd);
    }

    */
/**
     * @Description: 获取当前用户
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     *//*

    @RequestMapping(value = "getCurrentUser", method = RequestMethod.GET)
    public Result getCurrentUser() {
        SecurityUser securityUser = securityUtil.getSecurityUser();
        if (Objects.isNull(securityUser)) {
            return Result.error("未知用户");
        }
        return Result.success("获取当前用户成功", securityUser);
    }

    */
/**
     * @Description: 获取当前用户名
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     *//*

    @RequestMapping(value = "getCurrentUserName", method = RequestMethod.GET)
    public Result getCurrentUserName() {
        String userName = securityUtil.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return Result.error("未知用户");
        }
        return Result.success("获取当前用户名成功", userName);
    }

    */
/**
     * @Description: 密码验证
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     *//*

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
*/
