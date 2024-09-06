package com.action.common.common;

import com.action.common.core.constants.ActionRedisConstants;

public interface RedisSetConstants extends ActionRedisConstants {
    //验证信息部分
    String AUTH_TOKEN = AUTH + "token:"; //用户登录token令牌
    String AUTH_EMAIL = AUTH + "email:"; //用户email验证码
    String AUTH_PHONE = AUTH + "phone:"; //用户短信验证码
    //用户信息部分
    String USER_MENUPERM = USER + "menuPerm:"; //用户菜单权限
    String USER_BASIS = USER + "basis:"; //用户基础数据
    String USER_GROUP = USER + "group:"; //用户组数据
    String USER_ROLE = USER + "role:"; //用户角色数据
    String USER_POST = USER + "post:"; //用户岗位数据
    //其他信息部分
    String OTHER_WEBCLIENT = OTHER + "webclient:";
}
