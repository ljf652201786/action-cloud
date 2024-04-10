package com.action.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 范围表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_scope")
public class SysScope {
    @TableId(value = "id")
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("dept_id")
    private String deptId;
    @TableField("post_id")
    private Boolean postId;
    @TableField("status")
    private String status;
}
