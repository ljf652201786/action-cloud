package com.action.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description: 范围表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Boolean getPostId() {
        return postId;
    }

    public void setPostId(Boolean postId) {
        this.postId = postId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
