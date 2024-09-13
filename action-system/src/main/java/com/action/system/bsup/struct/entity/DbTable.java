package com.action.system.bsup.struct.entity;

import lombok.Data;

/**
 * @Description: 数据库表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
public class DbTable {
    //表目录
    private String TABLE_CATALOG;
    //表模式
    private String TABLE_SCHEMA;
    //表名
    private String TABLE_NAME;
    //表说明
    private String TABLE_COMMENT;
    //表引擎
    private String ENGINE;
    //表创建时间
    private String CREATE_TIME;
    //数据长度
    private long DATA_LENGTH;
    //最大数据长度
    private long MAX_DATA_LENGTH;
    //行格式
    private String ROW_FORMAT;
    //主键自动增量
    private long AUTO_INCREMENT;
    //表排序规则
    private String TABLE_COLLATION;
    //版本
    private String VERSION;
}
