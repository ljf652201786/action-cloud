package com.action.system.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 数据行限制表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_data_row_limit")
public class SysDataRowLimit extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("data_id")
    private String dataId;
    @TableField("type")
    private String type;
    @TableField("contact_id")
    private String contactId;
    @TableField("relation")
    private String relation;
    @TableField("limit_field")
    private String limitField;
    @TableField("limit_field_desc")
    private String limitFieldDesc;
    @TableField("limit_condition")
    private String limitCondition;
    @TableField("val")
    private String val;
    @TableField("status")
    private String status;
    @TableField(exist = false)
    private String tableName;
}
