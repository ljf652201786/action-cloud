package com.action.system.service.Impl;

import com.action.system.struct.entity.SysDataColumnLimit;
import com.action.system.mapper.SysDataColumnLimitMapper;
import com.action.system.service.ISysDataColumnLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Description: 数据列权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysDataColumnLimitServiceImpl extends ServiceImpl<SysDataColumnLimitMapper, SysDataColumnLimit> implements ISysDataColumnLimitService {
    private final SysDataColumnLimitMapper sysDataColumnLimitMapper;

    @Override
    public Set<SysDataColumnLimit> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        return sysDataColumnLimitMapper.getUserDataColumnPerm(groupIdSet, postIdSet, roleIdSet);
    }
}
