package com.action.business.entity;

import com.action.common.mybatisplus.extend.annotation.CorrelationField;
import com.action.common.mybatisplus.extend.annotation.CorrelationTables;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("biz_test")
public class Test {
//    @CorrelationTables({xxx.class})
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
//    @CorrelationField(table = xxx.class, column = "age")
//    @TableField(exist = false)
//    private Integer age;
}
