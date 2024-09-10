package com.action.system.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.struct.entity.SysApp;

public interface ISysAppService extends BaseMpService<SysApp> {
    void buildKeyPair(SysApp sysApp);

}
