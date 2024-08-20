package com.action.system.struct.converter;

import com.action.system.struct.dto.SysScheduleTaskDto;
import com.action.system.struct.entity.SysScheduleTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleTaskConverter {
    ScheduleTaskConverter INSTANCE = Mappers.getMapper(ScheduleTaskConverter.class);

    SysScheduleTaskDto sysScheduleTaskrToDto(SysScheduleTask sysScheduleTask);

}
