package com.action.system.bsup.service.impl;

import com.action.system.bsup.mapper.CommonMapper;
import com.action.system.bsup.service.ICommonService;
import com.action.system.bsup.struct.converter.DbTableColumnConverter;
import com.action.system.bsup.struct.converter.DbTableConverter;
import com.action.system.bsup.struct.entity.DbTable;
import com.action.system.bsup.struct.entity.DbTableColumn;
import com.action.system.bsup.struct.vo.DbTableColumnVo;
import com.action.system.bsup.struct.vo.DbTableVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 公共接口实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ICommonServiceImpl implements ICommonService {
    private final CommonMapper commonMapper;
    private final DbTableConverter dbTableConverter;
    private final DbTableColumnConverter dbTableColumnConverter;

    @Override
    public List<DbTableVo> getTables() {
        List<DbTable> dbTables = commonMapper.listTable();
        if (CollectionUtils.isEmpty(dbTables)) {
            return List.of();
        }
        return dbTables.stream().map(dbTable -> dbTableConverter.dbTableToDbTableVo(dbTable)).collect(Collectors.toList());
    }

    @Override
    public List<DbTableColumnVo> getTableColumn(String tableName) {
        List<DbTableColumn> dbTableColumns = commonMapper.listTableColumn(tableName);
        if (CollectionUtils.isEmpty(dbTableColumns)) {
            return List.of();
        }
        return dbTableColumns.stream().map(dbTableColumn -> dbTableColumnConverter.dbTableColumnToDbTableColumnVo(dbTableColumn)).collect(Collectors.toList());
    }
}
