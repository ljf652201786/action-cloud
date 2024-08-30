package com.action.third.service.corerain.service.impl;

import com.action.common.corerain.api.service._52.app.AppApi_52;
import com.action.common.corerain.api.service._52.auth.SysAuthApi_52;
import com.action.common.corerain.api.struct.dto.AppDeleteDto;
import com.action.common.corerain.api.struct.dto.AuthDto;
import com.action.common.corerain.api.struct.dto.LoginDto;
import com.action.common.corerain.api.struct.vo.AppInfoVo;
import com.action.common.corerain.api.struct.vo.AppPageVo;
import com.action.common.corerain.api.struct.vo.TokenVo;
import com.action.common.corerain.api.struct.vo.UserVo;
import com.action.common.network.struct.WebClientBody;
import com.action.third.service.corerain.service.SysAuthService;
import com.action.third.service.manager.service.IThirdPlatformInfoService;
import com.action.third.service.manager.service.IThirdPlatformLoginLogService;
import com.action.third.service.manager.struct.converter.ThirdPlatformInfoConverter;
import com.action.third.service.manager.struct.dto.AppCreateDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/26
 */
@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {
    private final Logger logger = LoggerFactory.getLogger(SysAuthServiceImpl.class);
    private final IThirdPlatformInfoService iThirdPlatformInfoService;
    private final IThirdPlatformLoginLogService iThirdPlatformLoginLogService;
    private final ThirdPlatformInfoConverter thirdPlatformInfoConverter;
    private final SysAuthApi_52 sysAuthApi_52;
    private final AppApi_52 appApi_52;

    /**
     * @param webClientBody body对象
     * @Description: 根据第三方文档文件解析出token
     * @return: String
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/26
     */
    @Override
    public String getToken(WebClientBody webClientBody) {
        AtomicReference<String> token = new AtomicReference<>();
        if (!StringUtils.isEmpty(webClientBody.getServiceUrl())) {
            iThirdPlatformInfoService.getThirdPlatformInfoByServiceUrl(webClientBody.getServiceUrl()).ifPresent(iThirdPlatformInfo -> {
                LoginDto loginDto = thirdPlatformInfoConverter.toLoginDto(iThirdPlatformInfo);
                UserVo userVo = sysAuthApi_52.login(loginDto);
                if (Objects.nonNull(userVo)) {
                    //异步将信息写入数据库作为记录
                    iThirdPlatformInfo.setUserToken(userVo.getUser_token());
                    boolean isUpdate = iThirdPlatformInfoService.updateById(iThirdPlatformInfo);
                    boolean isSave = iThirdPlatformLoginLogService.savelog(userVo, webClientBody.getServiceUrl());
                    if (isUpdate && isSave) {
                        if (StringUtils.isNotEmpty(iThirdPlatformInfo.getAppId()) && StringUtils.isNotEmpty(iThirdPlatformInfo.getAppSecret())) {
                            AuthDto authDto = thirdPlatformInfoConverter.toAuthDto(iThirdPlatformInfo);
                            TokenVo tokenVo = appApi_52.authenticate(authDto);
                            if (Objects.nonNull(tokenVo)) {
                                token.set(tokenVo.getAccessToken());
                            }
                        } else {
                            logger.error("Please first ensure that there are available app_id and app_secret");
                        }
                    }
                }
            });
        }
        return token.get();
    }

    @Override
    public boolean createApp(AppCreateDto appCreateDto) {
        boolean isCreate = false;
        if (StringUtils.isNotEmpty(appCreateDto.getServiceUrl())) {
            AppInfoVo appInfoVo = appApi_52.save(appCreateDto.getAppDto());
            if (Objects.nonNull(appInfoVo)) {
                isCreate = true;
                /*iThirdPlatformInfoService.getThirdPlatformInfoByServiceUrl("").ifPresent(iThirdPlatformInfo -> {
                    iThirdPlatformInfo.setAppId(appInfoVo.getApp_id());
                    iThirdPlatformInfo.setAppSecret(appInfoVo.getApp_secret());
                    iThirdPlatformInfoService.updateById(iThirdPlatformInfo);
                });*/
            }
        }
        return isCreate;
    }

    @Override
    public boolean deleteApp(AppDeleteDto appDeleteDto) {
        AtomicLong count = new AtomicLong(0L);
        AppPageVo appPageVo = appApi_52.appsPage(Integer.MAX_VALUE, 1, null);
        if (Objects.nonNull(appPageVo)) {
            appPageVo.getData().stream().filter(appInfoVo -> appInfoVo.getApp_uid().equalsIgnoreCase(appDeleteDto.getApp_uid())).findFirst().ifPresent(appInfoVo -> {
                count.set(iThirdPlatformInfoService.useCount(appInfoVo.getApp_id(), appInfoVo.getApp_secret()));
            });
        }
        if (count.get() > 0) {
            return false;
        }
        appApi_52.delete(appDeleteDto);
        return true;
    }
}
