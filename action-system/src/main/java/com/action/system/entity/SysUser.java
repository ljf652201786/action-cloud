package com.action.system.entity;

import com.action.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("avatar")
    private String avatar;
//    @SensitiveField(SensitiveTypeEnums.CHINESE_NAME)
    @TableField("user_name")
    private String username;
    @TableField("nick_name")
    private String nickName;
//    @SensitiveField(value = SensitiveTypeEnums.PASSWORD)
    @TableField("password")
    private String password;
    @TableField("email")
    private String email;
    @TableField("phone")
    private String phone;
    @TableField("sex")
    private String sex;
    @TableField("status")
    private String status;

}
