package com.action.third.service.corerain.controller;

import com.action.common.core.common.Result;
import com.action.common.corerain.api.service._52.usre.UserAPi_52;
import com.action.common.corerain.api.struct.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 鲲云平台用户控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@RequestMapping("corerain-user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserAPi_52 userAPi_52;

    /**
     * @param user_id 用户uuid
     * @Description: 获取用户
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/um/v1/user/get
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Result<UserInfoVo> getInfo(@RequestParam("user_id") String user_id) {
        return Result.success(userAPi_52.getInfo(user_id));
    }
}
