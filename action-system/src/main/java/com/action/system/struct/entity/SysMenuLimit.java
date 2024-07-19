package com.action.system.struct.entity;

import com.action.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description: 菜单权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu_limit")
public class SysMenuLimit extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("type")
    private String type;
    @TableField("contact_id")
    private String contactId;
    @TableField("menu_id")
    private String menuId;
    @TableField("status")
    private String status;

    public SysMenuLimit(String type, String contactId, String menuId, String status) {
        this.type = type;
        this.contactId = contactId;
        this.menuId = menuId;
        this.status = status;
    }
}
