package com.action.system.vo;

import lombok.Data;

/**
 * @Description: 用户配置文件
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/06/27
 */
@Data
public class UserProfileVO {
    private String id;
    private String avatar;
    private String username;
    private String nickName;
    private String email;
    private String phone;
    private String sex;
    private String status;
}
