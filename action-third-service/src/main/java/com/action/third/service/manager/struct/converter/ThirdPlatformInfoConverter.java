package com.action.third.service.manager.struct.converter;

import com.action.common.corerain.api.struct.dto.AuthDto;
import com.action.common.corerain.api.struct.dto.LoginDto;
import com.action.third.service.manager.struct.entity.ThirdPlatformInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ThirdPlatformInfoConverter {
    ThirdPlatformInfoConverter INSTANCE = Mappers.getMapper(ThirdPlatformInfoConverter.class);

    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password")
    })
    LoginDto toLoginDto(ThirdPlatformInfo thirdPlatformInfo);

    @Mappings({
            @Mapping(source = "appId", target = "app_id"),
            @Mapping(source = "appSecret", target = "app_secret")
    })
    AuthDto toAuthDto(ThirdPlatformInfo thirdPlatformInfo);

}
