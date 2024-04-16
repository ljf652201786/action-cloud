package com.action.auth.holder;

import com.action.call.clients.RemoteSystemClients;
import com.action.common.auth.holder.IAuthHolder;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.base.BaseSecurityRole;
import com.action.common.core.common.Result;
import com.action.common.entity.SecurityAuthUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 扩展数据权限接口
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/03/29
 */
@Service
public class ExtendAuthHolder implements IAuthHolder<SecurityAuthUser> {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RemoteSystemClients remoteSystemClients;

    /*
     * 手机登录
     * 缓存获取
     * */
    @Override
    public SecurityAuthUser getUserByPhone(String phone) {
        Result result = remoteSystemClients.getUserByPhone(phone);
        if (result.get("code").equals(HttpServletResponse.SC_OK)) {
            return new SecurityAuthUser(result.get("data"));
        }
        return null;
    }

    /*
     * 短信登录
     * 缓存获取
     * */
    @Override
    public SecurityAuthUser getUserByEmail(String email) {
        //通过邮箱从数据库查询user信息
        Result result = remoteSystemClients.getUserByEmail(email);
        if (result.get("code").equals(HttpServletResponse.SC_OK)) {
            return new SecurityAuthUser(result.get("data"));
        }
        return null;
    }

    /*
     * 微信扫码登录
     * 微信调用和数据库调用获取
     * */
    @Override
    public String getUserNameByWeChatCode(String code) {
        // 根据code拿到access_token和openid
        /*try {
            // 向微信获取
            WxOAuth2AccessToken accessToken = wxLoginUtilService.getAccessTokenAndOpenID(code);
            openid = wxLoginUtilService.getWxUserInfo(accessToken);
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }*/
        Result result = remoteSystemClients.getUserNameByWeChatCode(code);
        if (result.get("code").equals(HttpServletResponse.SC_OK)) {
            return result.get("data").toString();
        }
        return null;
    }

    /*
     * 通过用户名查找用户信息（包含菜单权限和角色）
     * 数据库配合缓存获取
     * */
    @Override
    public SecurityAuthUser getUserByUserName(String username) {
        Result result = remoteSystemClients.getUserByUserName(username);
        if (result.get("code").equals(HttpServletResponse.SC_OK)) {
            return (SecurityAuthUser) result.get("data");
        }
        return null;
    }

    /*
     * 获取系统所有的菜单权限(已配置缓存 IAuthHolder.class)
     * */
    @Override
    public Set<? extends BaseSecurityMenu> getSysPermission() {
        Result result = remoteSystemClients.getSysPermission();
        Set sysPermissions = (Set) result.get("data");
        return sysPermissions;
    }
}
