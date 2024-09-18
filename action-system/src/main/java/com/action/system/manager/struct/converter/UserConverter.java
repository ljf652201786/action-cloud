package com.action.system.manager.struct.converter;

import com.action.call.struct.vo.AuthUserInfoVo;
import com.action.common.helper.StructHelper;
import com.action.system.manager.struct.dto.SysUserDto;
import com.action.system.manager.struct.entity.SysUser;
import com.action.system.manager.struct.vo.UserProfileVO;
import com.action.system.manager.struct.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = StructHelper.class)
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mappings({
            @Mapping(source = "nickName", target = "nickname", qualifiedByName = "StringToByte")
    })
    AuthUserInfoVo sysUserToAuthUserVo(SysUser sysUser);

    UserProfileVO sysUserToUserProfileVO(SysUser sysUser);

    SysUser sysUserDtoToSysUser(SysUserDto sysUserDto);

    UserVo sysUserToUserVo(SysUser sysUser);

}
