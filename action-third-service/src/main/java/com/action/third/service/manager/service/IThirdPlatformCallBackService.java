package com.action.third.service.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.third.service.manager.struct.entity.ThirdPlatformCallBack;
import com.alibaba.fastjson.JSONObject;

public interface IThirdPlatformCallBackService extends BaseMpService<ThirdPlatformCallBack> {
    boolean analyzeAndSave(JSONObject jsonObject);
}
