package com.action.system.mapper;

import com.action.system.entity.SysRole;
import com.action.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户角色mapper
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<SysRole> getSysRoleByUserId(@Param("userId") String userId);
}
