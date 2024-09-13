package com.action.system.manager.struct.vo;

import com.action.common.mybatisplus.extend.annotation.CorrelationField;
import com.action.common.mybatisplus.extend.annotation.CorrelationTable;
import com.action.common.mybatisplus.extend.annotation.CorrelationTables;
import com.action.system.manager.struct.entity.SysDept;
import lombok.Data;

/**
 * @Description: 岗位Vo
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
public class PostVo {
    private String id;
    @CorrelationTables({@CorrelationTable(value = SysDept.class)})
    private String deptId;
    @CorrelationField(table = SysDept.class, column = "dept_name")
    private String deptName;
    private String postCode;
    private String postName;
    private Integer sort;
    private String status;
    private String remark;
}
