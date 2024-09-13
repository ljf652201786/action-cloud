package com.action.system.bsup.struct.converter;

import com.action.system.bsup.struct.entity.DbTableColumn;
import com.action.system.bsup.struct.vo.DbTableColumnVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DbTableColumnConverter {
    DbTableColumnConverter INSTANCE = Mappers.getMapper(DbTableColumnConverter.class);

    DbTableColumnVo dbTableColumnToDbTableColumnVo(DbTableColumn dbTableColumn);

}
