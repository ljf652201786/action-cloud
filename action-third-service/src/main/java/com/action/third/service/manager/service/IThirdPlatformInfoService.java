package com.action.third.service.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.third.service.manager.struct.entity.ThirdPlatformInfo;

import java.util.Optional;

public interface IThirdPlatformInfoService extends BaseMpService<ThirdPlatformInfo> {

    Optional<ThirdPlatformInfo> getThirdPlatformInfoByServiceUrl(String serviceUrl);

    long useCount(String app_id, String app_secret);
}
