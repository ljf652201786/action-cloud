package com.action.third.service.manager.service.impl;

import com.action.common.security.util.SecurityUtil;
import com.action.third.service.manager.mapper.ThirdPlatformRequestLogMapper;
import com.action.third.service.manager.service.IThirdPlatformRequestLogService;
import com.action.third.service.manager.struct.entity.ThirdPlatformRequestLog;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;


/**
 * @Description: 第三方平台请求日志实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Service
@RequiredArgsConstructor
public class IThirdPlatformRequestLogServiceImpl extends ServiceImpl<ThirdPlatformRequestLogMapper, ThirdPlatformRequestLog> implements IThirdPlatformRequestLogService {
    private final SecurityUtil securityUtil;

    @Override
    public boolean savelog(ClientResponse clientResponse) {
        ThirdPlatformRequestLog thirdPlatformRequestLog = new ThirdPlatformRequestLog();
        HttpRequest request = clientResponse.request();
        //192.168.1.219:8081
        thirdPlatformRequestLog.setAuthority(request.getURI().getAuthority());
        //url /um/user/login
        thirdPlatformRequestLog.setPath(request.getURI().getPath());
        //post
        thirdPlatformRequestLog.setMethodName(request.getMethod().name());
        //http://192.168.1.219:8081/um/user/login
        thirdPlatformRequestLog.setUrl(request.getURI().toString());
        //状态
        thirdPlatformRequestLog.setStatusCode(clientResponse.statusCode().value());
        //user_id
        thirdPlatformRequestLog.setUserId(securityUtil.getUserId());
        return save(thirdPlatformRequestLog);
    }
}
