package com.action.system.service.Impl;

import com.action.call.clients.RemoteAuthClients;
import com.action.common.common.UserSetConstants;
import com.action.common.core.common.Result;
import com.action.system.dto.SysUserExtend;
import com.action.system.entity.SysUser;
import com.action.system.mapper.SysUserMapper;
import com.action.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RemoteAuthClients authClients;

    @Override
    public Boolean regist(SysUserExtend sysUser) {
        Result result = authClients.generatePwd(sysUser.getPassword());
        String enablePwd = result.get("data").toString();
        if (StringUtils.isEmpty(enablePwd)) {
            return false;
        }
        sysUser.setPassword(enablePwd);
        sysUser.setAvatar(UserSetConstants.DEFAULT_AVATAR);
        boolean isSave = SqlHelper.retBool(sysUserMapper.insert(sysUser));
        return isSave;
    }

    @Override
    public Boolean editInfo(SysUser sysUser) {
        SysUser u = sysUserMapper.selectById(sysUser.getId());
        if (Objects.isNull(u)) {
            return false;
        }
        sysUser.setUsername(u.getUsername());//用户名不允许修改
        sysUser.setPassword(u.getPassword());//密码不允许修改
        return SqlHelper.retBool(sysUserMapper.updateById(u));
    }

    @Override
    public Boolean modifyPass(String rawPassword, String newPassword) {
        Result result = authClients.pwdMatches(rawPassword);
        if (result.get("code").equals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)) {
            return false;
        }
        String userId = result.get("data").toString();
        result = authClients.generatePwd(newPassword);
        String enablePwd = result.get("data").toString();
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(enablePwd);
        return SqlHelper.retBool(sysUserMapper.updateById(sysUser));
    }

    @Override
    public List<SysUser> getPesetPassOfUserListByIds(List<String> ids) {
        Result result = authClients.getDefaultPwd();
        String defaultPwd = result.get("data").toString();
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }
        List<SysUser> collect = ids.stream().map(id -> {
            SysUser sysUser = sysUserMapper.selectById(id);
            sysUser.setPassword(defaultPwd);
            return sysUser;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Boolean retrievePass(SysUser sysUser) {
        Result result = authClients.generatePwd(sysUser.getPassword());
        String enablePwd = result.get("data").toString();
        if (StringUtils.isEmpty(enablePwd)) {
            return false;
        }
        sysUser.setPassword(enablePwd);
        return SqlHelper.retBool(sysUserMapper.updateById(sysUser));
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
    }

    @Override
    public SysUser findByPhone(String phone) {
        SysUser user = new SysUser();
        user.setUsername("wangwu");
        return user;
//        return sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("phone", phone));
    }

    @Override
    public SysUser findByEmail(String email) {
        SysUser user = new SysUser();
        user.setUsername("wangwu");
        return user;
//        return sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("email", email));
    }

    @Override
    public String getUserNameByWeChatCode(String code) {
        return "wangwu";
    }

}
