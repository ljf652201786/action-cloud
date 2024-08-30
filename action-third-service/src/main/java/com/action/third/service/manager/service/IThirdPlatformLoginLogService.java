package com.action.third.service.manager.service;

import com.action.common.corerain.api.struct.vo.UserVo;
import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.third.service.manager.struct.entity.ThirdPlatformLoginLog;

public interface IThirdPlatformLoginLogService extends BaseMpService<ThirdPlatformLoginLog> {
    boolean savelog(UserVo userVo, String serviceUrl);
}
