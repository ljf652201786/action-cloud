package com.action.third.service.manager.service.impl;

import com.action.common.core.tool.StrUtils;
import com.action.common.corerain.api.struct.dto.CallBackContentDto;
import com.action.third.service.manager.mapper.ThirdPlatformCallBackContentMapper;
import com.action.third.service.manager.mapper.ThirdPlatformCallBackMapper;
import com.action.third.service.manager.service.IThirdPlatformCallBackService;
import com.action.third.service.manager.struct.entity.ThirdPlatformCallBack;
import com.action.third.service.manager.struct.entity.ThirdPlatformCallBackContent;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Description: 第三方平台回调信息实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Service
@RequiredArgsConstructor
public class IThirdPlatformCallBackServiceImpl extends ServiceImpl<ThirdPlatformCallBackMapper, ThirdPlatformCallBack> implements IThirdPlatformCallBackService {
    private final ThirdPlatformCallBackContentMapper thirdPlatformCallBackContentMapper;
    private final boolean isCallBackSave = false;

    @Override
    public boolean analyzeAndSave(JSONObject jsonObject) {
        String content = jsonObject.toJSONString();
        CallBackContentDto callBackContentDto = JSONObject.parseObject(content, CallBackContentDto.class);
        if (Objects.nonNull(callBackContentDto)) {
            boolean isCallBackContentSave = true;
            String contentId = null;
            if (isCallBackSave) {
                ThirdPlatformCallBackContent thirdPlatformCallBackContent = new ThirdPlatformCallBackContent();
                thirdPlatformCallBackContent.setContent(StrUtils.compressToString(content));//回调内容
                isCallBackContentSave = SqlHelper.retBool(thirdPlatformCallBackContentMapper.insert(thirdPlatformCallBackContent));
                contentId = isCallBackContentSave ? thirdPlatformCallBackContent.getId() : contentId;
            }
            if (isCallBackContentSave) {
                ThirdPlatformCallBack thirdPlatformCallBack = new ThirdPlatformCallBack();
                thirdPlatformCallBack.setContentId(contentId);//回调内容id
                thirdPlatformCallBack.setAlgorithmId(String.valueOf(callBackContentDto.getAlgorithm_id()));//算法id
                thirdPlatformCallBack.setAlgorithmName(callBackContentDto.getAlgorithm_name());//算法名称
                thirdPlatformCallBack.setDegree(Integer.toString(callBackContentDto.getDegree())); //算法等级
                thirdPlatformCallBack.setCameraName(callBackContentDto.getCamera_name());//监控点名称
                thirdPlatformCallBack.setTimestamp(callBackContentDto.getTimestamp());//事件时间
                thirdPlatformCallBack.setSrcPicName(callBackContentDto.getSrc_pic_name());//原始图片名称
                thirdPlatformCallBack.setSrcPicUrl(callBackContentDto.getSrc_pic_url());//原始图片链接
                thirdPlatformCallBack.setAlarmPicName(callBackContentDto.getAlarm_pic_name());//报警图片名称
                thirdPlatformCallBack.setAlarmPicUrl(callBackContentDto.getAlarm_pic_url());//报警图片链接
                thirdPlatformCallBack.setVideoName(callBackContentDto.getVideo_name());//视频片段名称
                thirdPlatformCallBack.setVideoUrl(callBackContentDto.getVideo_url());//视频片段链接
                return save(thirdPlatformCallBack);
            }
        }
        return false;
    }
}
