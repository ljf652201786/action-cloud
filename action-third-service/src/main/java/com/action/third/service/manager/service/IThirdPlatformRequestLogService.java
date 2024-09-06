package com.action.third.service.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.third.service.manager.struct.entity.ThirdPlatformRequestLog;
import org.springframework.web.reactive.function.client.ClientResponse;

public interface IThirdPlatformRequestLogService extends BaseMpService<ThirdPlatformRequestLog> {
    boolean savelog(ClientResponse clientResponse);
}
