package com.action.third.service.manager.service.impl;

import com.action.common.core.enums.StatusType;
import com.action.third.service.manager.mapper.ThirdPlatformInfoMapper;
import com.action.third.service.manager.service.IThirdPlatformInfoService;
import com.action.third.service.manager.struct.entity.ThirdPlatformInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description: 第三方平台信息实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Service
@RequiredArgsConstructor
public class IThirdPlatformInfoServiceImpl extends ServiceImpl<ThirdPlatformInfoMapper, ThirdPlatformInfo> implements IThirdPlatformInfoService {

    @Override
    public Optional<ThirdPlatformInfo> getThirdPlatformInfoByServiceUrl(String serviceUrl) {
        return this.getOneOpt(this.getLambdaQueryWrapper().eq(ThirdPlatformInfo::getServiceUrl, serviceUrl).eq(ThirdPlatformInfo::getStatus, StatusType.ENABLE.getStatus()));
    }

    @Override
    public long useCount(String app_id, String app_secret) {
        return this.count(getLambdaQueryWrapper().eq(ThirdPlatformInfo::getAppId, app_id).eq(ThirdPlatformInfo::getAppSecret, app_secret));
    }
}
