package com.action.system.sconf.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.sconf.struct.entity.SysApp;

public interface ISysAppService extends BaseMpService<SysApp> {
    void buildKeyPair(SysApp sysApp);

    boolean saveInfo(SysApp sysApp);

    boolean updateInfo(SysApp sysApp);

    boolean disable(String id);

    boolean enable(String id);
}
