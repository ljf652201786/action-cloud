package com.action.system.bsup.struct.entity;

import lombok.Data;

/**
 * @Description: 数据库表字段
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
public class DbTableColumn {
    //表目录
    private String TABLE_CATALOG;
    //表模式
    private String TABLE_SCHEMA;
    //表名
    private String TABLE_NAME;
    //表字段名称
    private String COLUMN_NAME;
    //表字段key(PRI 主键 )
    private String COLUMN_KEY;
    //是否为空
    private String IS_NULLABLE;
    //表字段说明
    private String COLUMN_COMMENT;
    //表字段类型
    private String COLUMN_TYPE;
    //表字段数据类型
    private String DATA_TYPE;
}
