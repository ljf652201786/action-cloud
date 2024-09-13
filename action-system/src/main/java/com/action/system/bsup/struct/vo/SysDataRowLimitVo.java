package com.action.system.bsup.struct.vo;

import com.action.common.mybatisplus.extend.annotation.CorrelationField;
import com.action.common.mybatisplus.extend.annotation.CorrelationTable;
import com.action.common.mybatisplus.extend.annotation.CorrelationTables;
import com.action.system.manager.struct.entity.SysData;
import lombok.Data;

/**
 * @Description: 数据行限制表VO
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
public class SysDataRowLimitVo {
    private String id;
    @CorrelationTables({@CorrelationTable(value = SysData.class)})
    private String dataId;
    @CorrelationField(table = SysData.class, column = "table_name")
    private String tableName;
    private String type;
    private String contactId;
    private String relation;
    private String limitField;
    private String limitFieldDesc;
    private String limitCondition;
    private String val;
    private String status;
}
