package com.action.system.bsup.struct.vo;

import lombok.Data;

/**
 * @Description: 数据库表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
public class DbTableVo {
    //表模式
    private String TABLE_SCHEMA;
    //表名
    private String TABLE_NAME;
    //表说明
    private String TABLE_COMMENT;
}
