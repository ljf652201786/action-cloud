package com.action.system.dto;

import com.action.common.mybatisplus.extend.annotation.Condition;

/**
 * @Description: 岗位查询参数类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
public class SysPostQuery {
    private String deptId;
    private String postCode;
    @Condition(Condition.ConditionTypeEnums.LIKE)
    private String postName;
}
