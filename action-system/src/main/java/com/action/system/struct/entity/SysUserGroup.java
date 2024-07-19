package com.action.system.struct.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户用户组中间表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_group")
public class SysUserGroup {
    @TableId(value = "id")
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("group_id")
    private String groupId;
    @TableField("status")
    private String status;

    public SysUserGroup(String userId, String groupId, String status) {
        this.userId = userId;
        this.groupId = groupId;
        this.status = status;
    }
}
