package com.action.system.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description: 多租户表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_multi_tenant")
public class SysMultiTenant extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("tenant_id")
    private String tenantId;
    @TableField("tenant_name")
    private String tenantName;
}
