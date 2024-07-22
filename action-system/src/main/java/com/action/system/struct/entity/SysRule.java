package com.action.system.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description: 规则表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_rule")
public class SysRule extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("rule_code")
    private String ruleCode;
    @TableField("limit_obj_id")
    private String limitObjId;
    @TableField("limit_type")
    private String limitType;
    @TableField("val")
    private String val;
    @TableField("status")
    private String status;
}
