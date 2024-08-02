package com.action.business.struct.vo;

import com.action.business.struct.entity.Test;
import com.action.common.mybatisplus.extend.annotation.CorrelationField;
import com.action.common.mybatisplus.extend.annotation.CorrelationTable;
import com.action.common.mybatisplus.extend.annotation.CorrelationTables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: test对象
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCpVo {
    @CorrelationTables({@CorrelationTable(value = Test.class, column = "testcp_id", and = "name='张三'")})
    private String id;
    private String age;
    private String TestId;
    @CorrelationField(table = Test.class, column = "name")
    private String name;
}
