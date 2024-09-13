package com.action.system.bsup.struct.converter;

import com.action.system.bsup.struct.entity.DbTable;
import com.action.system.bsup.struct.vo.DbTableVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DbTableConverter {
    DbTableConverter INSTANCE = Mappers.getMapper(DbTableConverter.class);

    DbTableVo dbTableToDbTableVo(DbTable dbTable);

}
