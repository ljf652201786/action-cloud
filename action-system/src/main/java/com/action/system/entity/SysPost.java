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
 * @Description: 岗位表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_post")
public class SysPost extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField(value = "dept_id")
    private String deptId;
    @TableField(value = "post_code")
    private String postCode;
    @Condition(Condition.ConditionTypeEnums.LIKE)
    @TableField(value = "post_name")
    private String postName;
    @TableField("sort")
    private Integer sort;
    @TableField("status")
    private String status;

}
