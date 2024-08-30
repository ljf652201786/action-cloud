package com.action.third.service.manager.service;

import com.action.common.corerain.api.struct.dto.CallBackContentDto;
import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.third.service.manager.struct.entity.ThirdPlatformCallBack;

public interface IThirdPlatformCallBackService extends BaseMpService<ThirdPlatformCallBack> {
    boolean analyzeAndSave(CallBackContentDto callBackContentDto);
}
