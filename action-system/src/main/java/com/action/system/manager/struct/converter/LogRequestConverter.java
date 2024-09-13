package com.action.system.manager.struct.converter;

import com.action.call.vo.LogRequestDto;
import com.action.system.manager.struct.entity.SysLogRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogRequestConverter {
    LogRequestConverter INSTANCE = Mappers.getMapper(LogRequestConverter.class);

    SysLogRequest logRequestDtoToSysLogRequest(LogRequestDto logRequestDto);

}
