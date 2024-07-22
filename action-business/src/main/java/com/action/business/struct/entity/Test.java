package com.action.business.struct.entity;

import com.action.common.core.service.ITreeNodeSelect;
import com.action.common.mybatisplus.extend.annotation.Condition;
import com.action.common.mybatisplus.extend.annotation.CorrelationField;
import com.action.common.mybatisplus.extend.annotation.CorrelationTables;
import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: test对象
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("biz_test")
public class Test extends BaseEntity /*implements ITreeNodeSelect*/ {
//    @CorrelationTables({xxx.class})
    @TableId("id")
    private String id;
//    @Condition(order = Condition.OrderTypeEnums.DESC, sort = 2)
    @TableField("name")
    private String name;
//    @CorrelationField(table = xxx.class, column = "age")
//    @TableField(exist = false)
//    private Integer age;



    /*@TableField(exist = false)
    private List<Test> childrenList = new ArrayList<>();

    @Override
    public void setChildrenList(List list) {
        this.childrenList = list;
    }*/

}
