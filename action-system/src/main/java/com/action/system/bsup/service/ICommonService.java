package com.action.system.bsup.service;

import com.action.system.bsup.struct.vo.DbTableColumnVo;
import com.action.system.bsup.struct.vo.DbTableVo;

import java.util.List;

public interface ICommonService {

    List<DbTableVo> getTables();

    List<DbTableColumnVo> getTableColumn(String tableName);
}
