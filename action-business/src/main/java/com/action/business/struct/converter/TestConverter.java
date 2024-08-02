package com.action.business.struct.converter;

import com.action.business.struct.entity.Test;
import com.action.business.struct.vo.TestImportVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TestConverter{
    TestConverter INSTANCE = Mappers.getMapper(TestConverter.class);

    Test importVoToTest(TestImportVo testImportVo);
}
