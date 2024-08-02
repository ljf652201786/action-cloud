package com.action.business.struct.converter;

import com.action.business.struct.entity.TestCp;
import com.action.business.struct.vo.TestCpVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TestCpConverter {
    TestCpConverter INSTANCE = Mappers.getMapper(TestCpConverter.class);

    TestCpVo testCpToVo(TestCp testcp);
}
