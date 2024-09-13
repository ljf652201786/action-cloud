package com.action.system.bsup.struct.vo;

import lombok.Data;

/**
 * @Description: 数据库表字段
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
public class DbTableColumnVo {
    //表模式
    private String TABLE_SCHEMA;
    //表名
    private String TABLE_NAME;
    //表字段名称
    private String COLUMN_NAME;
    //表字段说明
    private String COLUMN_COMMENT;
}
