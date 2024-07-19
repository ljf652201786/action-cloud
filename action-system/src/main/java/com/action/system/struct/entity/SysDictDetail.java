package com.action.system.struct.entity;

import com.action.common.base.BaseEntity;
import com.action.common.mybatisplus.extend.annotation.Condition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 字典详细表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_detail")
public class SysDictDetail extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("dict_id")
    private String dictId;
    @TableField("code")
    private String code;
    @TableField("label")
    private String label;
    @TableField("value")
    private String value;
    @Condition(order = Condition.OrderTypeEnums.ASC)
    @TableField("sort")
    private Integer sort;
    @TableField("status")
    private String status;
}
