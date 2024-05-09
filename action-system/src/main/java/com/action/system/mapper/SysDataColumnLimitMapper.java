package com.action.system.mapper;

import com.action.system.entity.SysDataColumnLimit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Description: 数据列权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
public interface SysDataColumnLimitMapper extends BaseMapper<SysDataColumnLimit> {
    Set<SysDataColumnLimit> getUserDataColumnPerm(@Param("groupIdSet") Set<String> groupIdSet, @Param("postIdSet") Set<String> postIdSet, @Param("roleIdSet") Set<String> roleIdSet);

}
