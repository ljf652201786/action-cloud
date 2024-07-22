package com.action.system.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.action.common.core.service.ITreeNodeSelect;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 部门表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dept")
public class SysDept extends BaseEntity implements ITreeNodeSelect {
    @TableId(value = "id")
    private String id;
    @TableField("ancestral")
    private String ancestral;
    @TableField("parent_id")
    private String parentId;
    @TableField("dept_code")
    private String deptCode;
    @TableField("dept_name")
    private String deptName;
    @TableField("sort")
    private Integer sort;
    @TableField("status")
    private String status;
    @TableField(exist = false)
    private List<SysDept> childrenList = new ArrayList<>();
    @TableField(exist = false)
    private List<SysPost> postList = new ArrayList<>();

    @Override
    public void setChildrenList(List list) {
        this.childrenList = list;
    }
}
