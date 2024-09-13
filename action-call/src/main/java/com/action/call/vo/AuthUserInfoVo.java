package com.action.call.vo;

import com.action.common.core.base.BaseSecurityUser;
import lombok.Data;

/**
 * @Description: 认证用户信息
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/22
 */
@Data
public class AuthUserInfoVo extends BaseSecurityUser {
    private String avatar;
    private String email;
    private String phone;
    private String sex;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;
}
