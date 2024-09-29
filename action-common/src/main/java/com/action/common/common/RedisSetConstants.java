package com.action.common.common;

import com.action.common.core.constants.ActionRedisConstants;

public interface RedisSetConstants extends ActionRedisConstants {
    //验证信息部分
    String AUTH_PHONE = AUTH + "phone:"; //用户短信验证码
    //其他信息部分
    String OTHER_WEBCLIENT = OTHER + "webclient:";
}
