package com.action.system.converter;

import com.action.call.vo.AuthUserInfoVo;
import com.action.system.entity.SysUser;
import com.action.system.vo.UserProfileVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    AuthUserInfoVo sysUserToAuthUserVo(SysUser sysUser);

    UserProfileVO sysUserToUserProfileVO(SysUser sysUser);

}
