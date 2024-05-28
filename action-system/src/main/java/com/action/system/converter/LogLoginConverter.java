package com.action.system.converter;

import com.action.call.vo.LogLoginVo;
import com.action.system.entity.SysLogLogin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogLoginConverter {
    LogLoginConverter INSTANCE = Mappers.getMapper(LogLoginConverter.class);

    SysLogLogin logLoginVoToSysLogLogin(LogLoginVo logLoginVo);

}
