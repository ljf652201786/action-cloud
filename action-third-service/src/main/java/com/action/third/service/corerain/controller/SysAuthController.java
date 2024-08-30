package com.action.third.service.corerain.controller;

import com.action.common.core.common.Result;
import com.action.common.corerain.api.service._52.auth.SysAuthApi_52;
import com.action.common.corerain.api.struct.dto.LoginDto;
import com.action.common.corerain.api.struct.dto.TokenDto;
import com.action.common.corerain.api.struct.vo.ResultVo;
import com.action.common.corerain.api.struct.vo.UserVo;
import com.action.common.network.struct.WebClientBody;
import com.action.third.service.corerain.service.SysAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 鲲云平台认证控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@RequestMapping("corerain-sysauth")
@RestController
@RequiredArgsConstructor
public class SysAuthController {
    private final SysAuthService sysAuthService;
    private final SysAuthApi_52 sysAuthApi_52;

    /**
     * @param loginDto 登录对象
     * @Description: 获取验证码
     * @return: Result
     * @link http://172.22.196.52:8081/um/user/check_code
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "checkCode", method = RequestMethod.POST)
    public Result checkCode(@RequestBody LoginDto loginDto) {
        return Result.success(sysAuthApi_52.checkCode(loginDto));
    }

    /**
     * @param tokenDto token对象
     * @Description: 用户登出
     * @return: Result
     * @link http://172.22.196.52:8081/um/user/logout
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResultVo logout(@RequestBody TokenDto tokenDto) {
        return sysAuthApi_52.logout(tokenDto);
    }

    /**
     * @param loginDto 登录对象
     * @Description: 用户登入
     * @return: Result
     * @link http://172.22.196.52:8081/um/user/login
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginDto loginDto) {
        UserVo userVo = sysAuthApi_52.login(loginDto);
        return Result.success(userVo);
    }

    /**
     * @param webClientBody
     * @Description: 获取第三方平台token方法
     * @return: Result
     * @link http://172.22.196.52:8081/um/user/check_code
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "getToken", method = RequestMethod.POST)
    public String getToken(@RequestBody WebClientBody webClientBody) {
        return sysAuthService.getToken(webClientBody);
    }
}
