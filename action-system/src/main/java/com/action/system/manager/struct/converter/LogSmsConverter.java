package com.action.system.manager.struct.converter;

import com.action.call.struct.dto.LogSMSDto;
import com.action.system.manager.struct.entity.SysLogSms;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogSmsConverter {
    LogSmsConverter INSTANCE = Mappers.getMapper(LogSmsConverter.class);

    SysLogSms logSMSDtoToSysLogSms(LogSMSDto logSMSDto);

}
