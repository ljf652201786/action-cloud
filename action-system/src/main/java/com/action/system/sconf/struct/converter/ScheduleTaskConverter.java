package com.action.system.sconf.struct.converter;

import com.action.system.sconf.struct.dto.SysScheduleTaskDto;
import com.action.system.sconf.struct.entity.SysScheduleTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleTaskConverter {
    ScheduleTaskConverter INSTANCE = Mappers.getMapper(ScheduleTaskConverter.class);

    SysScheduleTaskDto sysScheduleTaskrToDto(SysScheduleTask sysScheduleTask);

}
