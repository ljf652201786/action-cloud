package com.action.third.service.manager.struct.converter;

import com.action.common.corerain.api.struct.vo.UserVo;
import com.action.third.service.manager.struct.entity.ThirdPlatformLoginLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ThirdPlatformLoginLogConverter {
    ThirdPlatformLoginLogConverter INSTANCE = Mappers.getMapper(ThirdPlatformLoginLogConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "user_token", target = "userToken")
    })
    ThirdPlatformLoginLog toThirdPlatformLoginLog(UserVo userVo);
}
