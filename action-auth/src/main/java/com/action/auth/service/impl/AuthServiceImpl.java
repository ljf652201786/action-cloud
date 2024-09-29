package com.action.auth.service.impl;

import com.action.auth.service.IAuthService;
import com.action.call.clients.RemoteSystemClients;
import com.action.call.struct.vo.AuthUserInfoVo;
import com.action.common.core.constants.ActionRedisConstants;
import com.action.common.core.service.RedisCacheServices;
import com.action.common.core.tool.KeyPairUtils;
import com.action.common.network.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Description: 认证服务实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/17
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final RedisCacheServices redisCacheServices;
    private final RemoteSystemClients remoteSystemClients;

    @Override
    public void redirect(String appid, String url, String response_type, String scope) {
        if (StringUtils.isNoneBlank(response_type) &&
                StringUtils.isNoneBlank(scope) &&
                StringUtils.equals(response_type, "code") &&
                StringUtils.equals(scope, "openapi_login")) {
            //校验appid
            AuthUserInfoVo userByAppId = remoteSystemClients.getUserByAppId(appid);
            if (Objects.nonNull(userByAppId)) {
                //生成随机code
                String code = KeyPairUtils.getAppId();
                redisCacheServices.set(ActionRedisConstants.OPEN_CODE_KEY + appid, code);
                //重定向到url
                HashMap<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("code", code);
                WebClientUtils.get(url, null, paramsMap, String.class);
                return;
            }
        }
        throw new RuntimeException("错误的请求，无法进行认证");
    }
}
