package com.action.system.struct.converter;

import com.action.call.vo.AuthUserInfoVo;
import com.action.common.helper.StructHelper;
import com.action.system.struct.entity.SysUser;
import com.action.system.struct.vo.UserProfileVO;
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

}
