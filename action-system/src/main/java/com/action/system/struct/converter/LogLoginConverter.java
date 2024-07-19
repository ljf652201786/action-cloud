package com.action.system.struct.converter;

import com.action.call.vo.LogLoginVo;
import com.action.system.struct.entity.SysLogLogin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogLoginConverter {
    LogLoginConverter INSTANCE = Mappers.getMapper(LogLoginConverter.class);

    SysLogLogin logLoginVoToSysLogLogin(LogLoginVo logLoginVo);

}
