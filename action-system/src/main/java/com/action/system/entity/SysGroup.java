package com.action.system.entity;

import com.action.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户组表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_group")
public class SysGroup extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("group_code")
    private String groupCode;
    @TableField("group_name")
    private String groupName;
    @TableField("sort")
    private Integer sort;
    @TableField("status")
    private String status;
}
