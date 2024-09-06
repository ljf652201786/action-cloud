package com.action.business.struct.entity;

import com.action.common.core.service.ITreeNodeSelect;
import com.action.common.mybatisplus.extend.annotation.Condition;
import com.action.common.mybatisplus.extend.annotation.CorrelationTable;
import com.action.common.mybatisplus.extend.annotation.CorrelationTables;
import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
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
public class Test extends BaseEntity/*implements ITreeNodeSelect*/ {
    @TableId("id")
    @CorrelationTables({@CorrelationTable(value = Test.class, column = "testcp_id", and = "name='张三'")})
    private String id;
    //    @Condition(order = Condition.OrderTypeEnums.DESC, sort = 2)
    @TableField("name")
    private String name;
    @TableField("testcp_id")
    private String testcpId;
    /*@TableField(exist = false)
    private List<Test> childrenList = new ArrayList<>();
    @Override
    public void setChildrenList(List list) {
        this.childrenList = list;
    }*/

    @TableField(exist = false)
    private List<TestCp> testCpList;

    @Condition(
            value = Condition.ConditionTypeEnums.BETWEEN,
            order = Condition.OrderTypeEnums.DESC
    )
    @TableField(
            fill = FieldFill.INSERT
    )
    private String createTime;

}
