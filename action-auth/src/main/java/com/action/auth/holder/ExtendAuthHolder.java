package com.action.auth.holder;

import com.action.auth.entity.SecurityAuthUser;
import com.action.call.clients.RemoteSystemClients;
import com.action.common.auth.base.BaseSecurityMenu;
import com.action.common.auth.base.BaseSecurityRole;
import com.action.common.auth.holder.IAuthHolder;
import com.action.common.core.common.Result;
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
        //通过手机号从数据库查询user信息
        /*SecurityAuthUser user = new SecurityAuthUser();
        user.setUsername("wangwu");
        user.setCode("1234");
        return user;*/
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
        /*SecurityAuthUser user = new SecurityAuthUser();
        user.setUsername("wangwu");
        user.setCode("1234");
        return user;*/
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
     * 用户名密码登录
     * 数据库配合缓存获取
     * */
    @Override
    public SecurityAuthUser getUserByUserName(String username) {
        //通过用户名查找用户信息（包含菜单权限和角色）
        SecurityAuthUser user = new SecurityAuthUser();
        user.setId("1770275787111649281");
        user.setUsername("wangwu");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setStatus("1");

        BaseSecurityRole baseSecurityRole = new BaseSecurityRole();
        baseSecurityRole.setCode("ROLE_user");
        Set<BaseSecurityRole> baseSecurityRoles = new HashSet<>();
        baseSecurityRoles.add(baseSecurityRole);
        user.setRoleScopeList(baseSecurityRoles);

        Set<BaseSecurityMenu> baseSecurityMenus = new HashSet<>();
        BaseSecurityMenu baseSecurityMenu = new BaseSecurityMenu();
        baseSecurityMenu.setPath("/system/user/listPage");
        baseSecurityMenu.setPerms("sys::user::list");
        baseSecurityMenus.add(baseSecurityMenu);
        BaseSecurityMenu baseSecurityMenu1 = new BaseSecurityMenu();
        baseSecurityMenu1.setPath("/system/user/private");
        baseSecurityMenu1.setPerms("sys::user::private");
        baseSecurityMenus.add(baseSecurityMenu1);
        user.setMenuScopeList(baseSecurityMenus);

        return user;
    }

    /*
     * 获取系统所有的菜单权限(已配置缓存 IAuthHolder.class)
     * */
    @Override
    public Set<? extends BaseSecurityMenu> getSysPermission() {
        Set<BaseSecurityMenu> baseSecurityMenus = new HashSet<>();
        BaseSecurityMenu baseSecurityMenu = new BaseSecurityMenu();
        baseSecurityMenu.setPath("/system/user/listPage");
        baseSecurityMenu.setPerms("sys::user::list");
        baseSecurityMenus.add(baseSecurityMenu);
        BaseSecurityMenu baseSecurityMenu1 = new BaseSecurityMenu();
        baseSecurityMenu1.setPath("/system/user/private");
        baseSecurityMenu1.setPerms("sys::user::private");
        baseSecurityMenus.add(baseSecurityMenu1);
        return baseSecurityMenus;
    }
}
