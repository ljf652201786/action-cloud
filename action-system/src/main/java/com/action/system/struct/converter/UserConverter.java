package com.action.system.struct.converter;

import com.action.call.vo.AuthUserInfoVo;
import com.action.system.struct.entity.SysUser;
import com.action.system.struct.vo.UserProfileVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    AuthUserInfoVo sysUserToAuthUserVo(SysUser sysUser);

    UserProfileVO sysUserToUserProfileVO(SysUser sysUser);

}
