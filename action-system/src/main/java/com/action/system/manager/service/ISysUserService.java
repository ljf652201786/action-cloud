package com.action.system.manager.service;

import com.action.call.vo.AuthUserInfoVo;
import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.dto.SysUserDto;
import com.action.system.manager.struct.entity.SysUser;
import com.action.system.manager.struct.vo.UserProfileVO;
import com.action.system.manager.struct.vo.UserVo;

import java.util.List;

public interface ISysUserService extends BaseMpService<SysUser> {

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return Boolean
     */
    UserVo getUserInfoById(String id);

    /**
     * 保存用户信息
     *
     * @param sysUserDto 用户对象
     * @return Boolean
     */
    boolean saveInfo(SysUserDto sysUserDto);

    /**
     * 更新用户信息
     *
     * @param sysUserDto 用户对象
     * @return Boolean
     */
    boolean updateInfo(SysUserDto sysUserDto);

    /**
     * 用户注册
     *
     * @param sysUser 用户注册对象
     * @return Boolean
     */
    boolean regist(SysUserDto sysUser);

    /**
     * 获取个人信息
     *
     * @return Boolean
     */
    UserProfileVO getUserProfile();

    /**
     * 修改个人信息
     *
     * @param sysUser 用户注册对象
     * @return Boolean
     */
    boolean editInfo(SysUser sysUser);

    boolean disable(String id);

    boolean enable(String id);

    /**
     * 设置用户默认角色
     *
     * @param sysUserDto 用户扩展对象
     * @return Boolean
     */
    void setUserDefaultRole(SysUserDto sysUserDto);

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
     * 通过openid获取用户信息
     *
     * @param openid openid
     * @return SysUser
     */
    AuthUserInfoVo findByOpenId(String openid);

    /**
     * 通过应用id获取用户信息
     *
     * @param appid 应用id
     * @return SysUser
     */
    AuthUserInfoVo findByAppId(String appid);

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
     * @param sysUserDto 用户扩展对象
     * @return void
     */
    void saveUserExtendInfo(SysUserDto sysUserDto);

    /**
     * 更新用户扩展信息（包含菜单权限和角色）
     *
     * @param sysUserDto 用户扩展对象
     * @return SecurityAuthUser
     */
    void updateUserExtendInfo(SysUserDto sysUserDto);
}
