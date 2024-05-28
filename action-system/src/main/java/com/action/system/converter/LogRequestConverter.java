package com.action.system.converter;

import com.action.call.vo.LogRequestVo;
import com.action.system.entity.SysLogRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogRequestConverter {
    LogRequestConverter INSTANCE = Mappers.getMapper(LogRequestConverter.class);

    SysLogRequest logRequestVoToSysLogRequest(LogRequestVo logRequestVo);

}
