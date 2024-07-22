package com.action.system.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 数据限制字段表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_data_column_limit")
public class SysDataColumnLimit extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("data_id")
    private String dataId;
    @TableField("type")
    private String type;
    @TableField("contact_id")
    private String contactId;
    @TableField("limit_field")
    private String limitField;
    @TableField("limit_field_desc")
    private String limitFieldDesc;
    @TableField(exist = false)
    private String tableName;

    public SysDataColumnLimit(String dataId, String type, String contactId, String limitField, String limitFieldDesc) {
        this.dataId = dataId;
        this.type = type;
        this.contactId = contactId;
        this.limitField = limitField;
        this.limitFieldDesc = limitFieldDesc;
    }
}
