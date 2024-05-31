package com.action.business.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TestConverter {
    TestConverter INSTANCE = Mappers.getMapper(TestConverter.class);

}
