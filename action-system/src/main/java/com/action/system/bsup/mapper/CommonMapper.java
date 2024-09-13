package com.action.system.bsup.mapper;

import com.action.system.bsup.struct.entity.DbTable;
import com.action.system.bsup.struct.entity.DbTableColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {

    List<DbTable> listTable();

    List<DbTableColumn> listTableColumn(String tableName);
}
