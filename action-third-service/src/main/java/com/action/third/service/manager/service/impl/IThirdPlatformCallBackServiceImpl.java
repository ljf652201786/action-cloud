package com.action.third.service.manager.service.impl;

import com.action.common.core.tool.JsonUtils;
import com.action.common.corerain.api.struct.dto.CallBackContentDto;
import com.action.third.service.manager.mapper.ThirdPlatformCallBackMapper;
import com.action.third.service.manager.service.IThirdPlatformCallBackService;
import com.action.third.service.manager.struct.entity.ThirdPlatformCallBack;
import com.action.third.service.util.RequestUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 第三方平台回调信息实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Service
@RequiredArgsConstructor
public class IThirdPlatformCallBackServiceImpl extends ServiceImpl<ThirdPlatformCallBackMapper, ThirdPlatformCallBack> implements IThirdPlatformCallBackService {

    @Override
    public boolean analyzeAndSave(CallBackContentDto callBackContentDto) {
        ThirdPlatformCallBack thirdPlatformCallBack = new ThirdPlatformCallBack();
        thirdPlatformCallBack.setContent(JsonUtils.toJson(callBackContentDto));
        thirdPlatformCallBack.setUrl(RequestUtils.getCurrentRequest().getServletPath());
        return save(thirdPlatformCallBack);
    }
}
