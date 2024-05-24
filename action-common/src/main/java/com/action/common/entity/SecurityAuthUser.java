/*
package com.action.common.entity;

import com.action.common.core.base.BaseSecurityUser;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;

*/
/**
 * @Description: Security认证用户
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/03/30
 *//*

public class SecurityAuthUser extends BaseSecurityUser {
    private String avatar;
    private String nickName;
    private String email;
    private String sex;

    public SecurityAuthUser() {
    }

    public SecurityAuthUser(Object o) {
        if (Objects.nonNull(o)) {
            Map map;
            if (o instanceof Map) {
                map = (Map) o;
            } else {
                try {
                    map = BeanUtils.describe(o);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                BeanUtils.populate(this, map);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
*/
