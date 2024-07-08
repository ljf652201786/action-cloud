package com.action.system.entity;

import com.action.common.base.BaseEntity;
import com.action.common.mybatisplus.extend.annotation.Condition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 角色表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class SysRole extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("role_code")
    private String roleCode;
    @TableField("role_name")
    private String roleName;
    @TableField("default_role")
    private Boolean defaultRole;
    @TableField("sort")
    private Integer sort;
    @Condition(Condition.ConditionTypeEnums.EQ)
    @TableField("status")
    private String status;

}
