package com.action.common.common;

public interface RedisSetConstants {
    //验证信息部分
    String AUTH = "auth:";
    String AUTH_TOKEN = "auth:token:"; //用户登录token令牌
    String AUTH_EMAIL = "auth:email:"; //用户email验证码
    String AUTH_PHONE = "auth:phone:"; //用户短信验证码
    //用户信息部分
    String USER = "user:";
    String USER_MENUPERM = "user:menuPerm:"; //用户菜单权限
    String USER_DATAPERM_ROW = "user:dataPerm:row:"; //用户行数据权限
    String USER_DATAPERM_COLUMN = "user:dataPerm:column:"; //用户列数据权限
    String USER_BASIS = "user:basis:"; //用户基础数据
    String USER_GROUP = "user:group:"; //用户组数据
    String USER_ROLE = "user:role:"; //用户角色数据
    String USER_POST = "user:post:"; //用户岗位数据
    String USER_OTHER = "user:other:"; //用户其他数据
    //其他信息部分
    String OTHER = "other:";

}
