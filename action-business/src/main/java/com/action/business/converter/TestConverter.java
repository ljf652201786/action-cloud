package com.action.business.converter;

import com.action.business.entity.Test;
import com.action.business.vo.TestImportVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TestConverter {
    TestConverter INSTANCE = Mappers.getMapper(TestConverter.class);

    Test importVoToTest(TestImportVo testImportVo);
}
