package com.action.system.struct.entity;

import com.action.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 数据表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_data")
public class SysData extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("parent_id")
    private String parentId;
    @TableField("data_code")
    private String dataCode;
    @TableField("label")
    private String label;
    @TableField("table_name")
    private String tableName;
    @TableField("sort")
    private Integer sort;
    @TableField("status")
    private String status;
    @TableField(exist = false)
    private List<SysData> childrenDataList;
}
