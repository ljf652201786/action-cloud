package com.action.system.service.Impl;

import com.action.common.core.tool.KeyPairUtils;
import com.action.system.mapper.SysAppMapper;
import com.action.system.service.ISysAppService;
import com.action.system.struct.entity.SysApp;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 应用表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysAppServiceImpl extends ServiceImpl<SysAppMapper, SysApp> implements ISysAppService {
    private final SysAppMapper sysAppMapper;

    @Override
    public void buildKeyPair(SysApp sysApp) {
        String appId = KeyPairUtils.getAppId();
        String appSecret = KeyPairUtils.getAppSecret(appId);
        sysApp.setAppId(appId);
        sysApp.setAppSecret(appSecret);
    }
}
