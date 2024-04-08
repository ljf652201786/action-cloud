package com.action.system.service;

import com.action.system.dto.SysUserExtend;
import com.action.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ISysUserService extends IService<SysUser> {

    /**
     * 用户注册
     *
     * @param sysUser 用户注册对象
     * @return Boolean
     */
    Boolean regist(SysUserExtend sysUser);

    /**
     * 修改个人信息
     *
     * @param sysUser 用户注册对象
     * @return Boolean
     */
    Boolean editInfo(SysUser sysUser);

    /**
     * 修改密码
     *
     * @param rawPassword 原始密码（未加密）
     * @param newPassword 新密码
     * @return Boolean
     */
    Boolean modifyPass(String rawPassword, String newPassword);

    /**
     * 根据id集合获取重置密码的用户列表
     *
     * @param ids 用户id集合
     * @return Boolean
     */
    List<SysUser> getPesetPassOfUserListByIds(List<String> ids);

    /**
     * 找回密码
     *
     * @param sysUser 用户扩展对象
     * @return Boolean
     */
    Boolean retrievePass(SysUser sysUser);

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser findByUsername(String username);

    /**
     * 通过手机号获取用户信息
     *
     * @param phone 手机号
     * @return SysUser
     */
    SysUser findByPhone(String phone);

    /**
     * 通过邮箱获取用户信息
     *
     * @param email 邮箱
     * @return SysUser
     */
    SysUser findByEmail(String email);

    /**
     * 微信扫码获取用户名
     *
     * @param code 微信扫码code
     * @return String
     */
    String getUserNameByWeChatCode(String code);
}
