package com.action.system.service;

import com.action.call.vo.AuthUserInfoVo;
import com.action.system.dto.SysUserExtend;
import com.action.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 设置用户默认角色
     *
     * @param sysUserExtend 用户扩展对象
     * @return Boolean
     */
    void setUserDefaultRole(SysUserExtend sysUserExtend);

    /**
     * 修改密码
     *
     * @param rawPassword 原始密码（未加密）
     * @param newPassword 新密码
     * @return Boolean
     */
    Boolean modifyPass(String rawPassword, String newPassword);

    /**
     * 根据id集合重置用户密码
     *
     * @param ids 用户id集合
     * @return Boolean
     */
    Boolean resetPassBatchByIds(List<String> ids);

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
    AuthUserInfoVo findByPhone(String phone);

    /**
     * 通过邮箱获取用户信息
     *
     * @param email 邮箱
     * @return SysUser
     */
    AuthUserInfoVo findByEmail(String email);

    /**
     * 微信扫码获取用户名
     *
     * @param code 微信扫码code
     * @return String
     */
    String getUserNameByWeChatCode(String code);

    /**
     * 通过用户名查找用户信息（包含菜单权限和角色）
     *
     * @param username 用户名
     * @return SecurityAuthUser
     */
    AuthUserInfoVo getUserByUserName(String username);

    /**
     * 保存用户扩展信息（包含菜单权限和角色）
     *
     * @param sysUserExtend 用户扩展对象
     * @return void
     */
    void saveUserExtendInfo(SysUserExtend sysUserExtend);

    /**
     * 更新用户扩展信息（包含菜单权限和角色）
     *
     * @param sysUser       用户对象
     * @param sysUserExtend 用户扩展对象
     * @return SecurityAuthUser
     */
    void updateUserExtendInfo(SysUser sysUser, SysUserExtend sysUserExtend);
}
