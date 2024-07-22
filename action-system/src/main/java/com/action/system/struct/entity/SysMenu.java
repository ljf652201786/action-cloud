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
 * @Description: 菜单表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class SysMenu extends BaseEntity implements ITreeNodeSelect {
    @TableId(value = "id")
    private String id;
    @TableField("ancestral")
    private String ancestral;
    @TableField("parent_id")
    private String parentId;
    @TableField("menu_name")
    private String menuName;
    @TableField("menu_type")
    private String menuType;
    @TableField("menu_icon")
    private String menuIcon;
    @TableField("menu_perm")
    private String menuPerm;
    @TableField("route_name")
    private String routeName;
    @TableField("route_url")
    private String routeUrl;
    @TableField("component")
    private String component;
    @TableField("is_frame")
    private Integer isFrame;
    @TableField("iframe_url")
    private String iframeUrl;
    @TableField("is_cache")
    private Integer isCache;
    @TableField("sort")
    private Integer sort;
    @TableField("visible")
    private String visible;
    @TableField("status")
    private String status;
    @TableField("enter_transition")
    private String enterTransition;
    @TableField("leave_transition")
    private String leaveTransition;
    @TableField("frame_loading")
    private String frameLoading;
    @TableField("hidden_tag")
    private String hiddenTag;
    @TableField(exist = false)
    private List<SysMenu> childrenList = new ArrayList<>();

    @Override
    public void setChildrenList(List list) {
        this.childrenList = list;
    }
}
