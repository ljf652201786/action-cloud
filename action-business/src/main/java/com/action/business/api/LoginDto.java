package com.action.business.api;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/22
 */
public class LoginDto {
    //用户名
    private String username;
    //密码
    private String password;
    //验证码
    private String check_code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }
}
