package com.action.third.service.manager.service.impl;

import com.action.common.corerain.api.struct.vo.UserVo;
import com.action.common.mybatisplus.extend.utils.RequestUtils;
import com.action.third.service.manager.mapper.ThirdPlatformLoginLogMapper;
import com.action.third.service.manager.service.IThirdPlatformLoginLogService;
import com.action.third.service.manager.struct.converter.ThirdPlatformLoginLogConverter;
import com.action.third.service.manager.struct.entity.ThirdPlatformLoginLog;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @Description: 第三方平台登录日志实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Service
@RequiredArgsConstructor
public class IThirdPlatformLoginLogServiceImpl extends ServiceImpl<ThirdPlatformLoginLogMapper, ThirdPlatformLoginLog> implements IThirdPlatformLoginLogService {
    private final ThirdPlatformLoginLogConverter thirdPlatformLoginLogConverter;

    @Override
    public boolean savelog(UserVo userVo, String serviceUrl) {
        ThirdPlatformLoginLog thirdPlatformLoginLog = thirdPlatformLoginLogConverter.toThirdPlatformLoginLog(userVo);
        thirdPlatformLoginLog.setId(null);
        thirdPlatformLoginLog.setServiceUrl(serviceUrl);
        thirdPlatformLoginLog.setUrl(RequestUtils.getCurrentRequest().getRequestURI());
        return save(thirdPlatformLoginLog);
    }
}
