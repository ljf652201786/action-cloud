package com.action.system.struct.converter;

import com.action.call.vo.LogSMSVo;
import com.action.system.struct.entity.SysLogSms;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogSmsConverter {
    LogSmsConverter INSTANCE = Mappers.getMapper(LogSmsConverter.class);

    SysLogSms logSMSVoToSysLogSms(LogSMSVo logSMSVo);

}
