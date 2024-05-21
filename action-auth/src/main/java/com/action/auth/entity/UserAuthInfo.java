package com.action.auth.entity;/*
package com.action.auth.server.entity;

import lombok.Data;

import java.util.Set;

*/
/**
 * @Description: 用户认证信息传输层对象
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/5/20
 *//*

@Data
public class UserAuthInfo {

    */
/**
     * 用户ID
     *//*

    private String userId;

    */
/**
     * 用户名
     *//*

    private String username;

    */
/**
     * 用户密码
     *//*

    private String password;

    */
/**
     * 用户状态(1:正常;0:禁用)
     *//*

    private String status;

    */
/**
     * 用户角色编码集合 ["ROOT","ADMIN"]
     *//*

    private Set<String> roles;

    */
/**
     * 用户权限标识集合
     *//*

    private Set<String> perms;

    */
/**
     * 部门ID
     *//*

    private Long deptId;

    */
/**
     * 数据权限范围
     *//*

    private Integer dataScope;


    */
/**
     * 昵称(OIDC UserInfo)
     *//*

    private String nickname;

    */
/**
     * 手机号(OIDC UserInfo)
     *//*

    private String mobile;

    */
/**
     * 邮箱(OIDC UserInfo)
     *//*

    private String email;

    */
/**
     * 头像(OIDC UserInfo)
     *//*

    private String avatar;

}
*/
