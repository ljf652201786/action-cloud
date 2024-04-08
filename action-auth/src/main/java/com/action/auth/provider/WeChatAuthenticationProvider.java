/*
package com.action.auth.provider;

import com.action.common.auth.token.WeChatAuthenticationToken;
import com.action.common.auth.user.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

*/
/**
 * @Description: 扩展
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/03/26
 *//*


public class WeChatAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WeChatAuthenticationToken weChatAuthenticationToken = (WeChatAuthenticationToken) authentication;
        String username = weChatAuthenticationToken.getUsername();
        // 如果验证码一致，从数据库中读取该手机号对应的用户信息
        SecurityUser loadedUser = (SecurityUser) userDetailsService.loadUserByUsername(username);
        if (loadedUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new WeChatAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (WeChatAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
*/
