package com.action.system.sconf.service.impl;

import com.action.common.core.tool.KeyPairUtils;
import com.action.common.enums.StatusType;
import com.action.system.sconf.mapper.SysAppMapper;
import com.action.system.sconf.service.ISysAppService;
import com.action.system.sconf.struct.entity.SysApp;
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

    @Override
    public boolean saveInfo(SysApp sysApp) {
        buildKeyPair(sysApp);
        return this.saveOrUpdate(sysApp);
    }

    @Override
    public boolean updateInfo(SysApp sysApp) {
        return this.update(this.getLambdaUpdateWrapper().set(SysApp::getAppName, sysApp.getAppName()).set(SysApp::getStatus, sysApp.getStatus()).eq(SysApp::getId, sysApp.getId()));
    }

    @Override
    public boolean disable(String id) {
        return this.update(this.getLambdaUpdateWrapper().set(SysApp::getStatus, StatusType.DISABLED.getStatus()).eq(SysApp::getId, id));
    }

    @Override
    public boolean enable(String id) {
        return this.update(this.getLambdaUpdateWrapper().set(SysApp::getStatus, StatusType.ENABLE.getStatus()).eq(SysApp::getId, id));
    }
}
