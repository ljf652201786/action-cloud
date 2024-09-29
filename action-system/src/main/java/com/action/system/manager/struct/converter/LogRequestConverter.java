package com.action.system.manager.struct.converter;

import com.action.common.core.entity.LogRequestStruct;
import com.action.system.helper.StructHelper;
import com.action.system.manager.struct.entity.SysLogRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = StructHelper.class)
public interface LogRequestConverter {
    LogRequestConverter INSTANCE = Mappers.getMapper(LogRequestConverter.class);

    @Mappings({
            @Mapping(source = "loginStatus", target = "loginStatus", qualifiedByName = "booleanToStr")
    })
    SysLogRequest logRequestStructToSysLogRequest(LogRequestStruct logRequestStruct);

}
