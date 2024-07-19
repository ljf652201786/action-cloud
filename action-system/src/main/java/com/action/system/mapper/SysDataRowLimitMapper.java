package com.action.system.mapper;

import com.action.system.struct.entity.SysDataRowLimit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @Description: 数据行权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/07
 */
public interface SysDataRowLimitMapper extends BaseMapper<SysDataRowLimit> {
    Set<SysDataRowLimit> getUserDataRowPerm(@Param("groupIdSet") Set<String> groupIdSet, @Param("postIdSet") Set<String> postIdSet, @Param("roleIdSet") Set<String> roleIdSet);

}
