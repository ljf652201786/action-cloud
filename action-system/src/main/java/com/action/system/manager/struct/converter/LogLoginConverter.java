package com.action.system.manager.struct.converter;

import com.action.call.vo.LogLoginDto;
import com.action.system.manager.struct.entity.SysLogLogin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogLoginConverter {
    LogLoginConverter INSTANCE = Mappers.getMapper(LogLoginConverter.class);

    SysLogLogin logLoginDtoToSysLogLogin(LogLoginDto logLoginDto);

}
