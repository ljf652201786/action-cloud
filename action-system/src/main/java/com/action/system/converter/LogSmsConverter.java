package com.action.system.converter;

import com.action.call.vo.LogSMSVo;
import com.action.system.entity.SysLogSms;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogSmsConverter {
    LogSmsConverter INSTANCE = Mappers.getMapper(LogSmsConverter.class);

    SysLogSms logSMSVoToSysLogSms(LogSMSVo logSMSVo);

}
