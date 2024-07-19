package com.action.system.struct.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description: 菜单规则中间表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu_rule")
public class SysMenuRule {
    @TableId(value = "id")
    private String id;
    @TableField("menu_id")
    private String menuId;
    @TableField("rule_id")
    private String ruleId;

    public SysMenuRule(String menuId, String ruleId) {
        this.menuId = menuId;
        this.ruleId = ruleId;
    }
}
