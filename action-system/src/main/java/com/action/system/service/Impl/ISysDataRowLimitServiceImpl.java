package com.action.system.service.Impl;

import com.action.system.struct.entity.SysDataRowLimit;
import com.action.system.mapper.SysDataRowLimitMapper;
import com.action.system.service.ICacheService;
import com.action.system.service.ISysDataRowLimitService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * @Description: 数据行权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysDataRowLimitServiceImpl extends ServiceImpl<SysDataRowLimitMapper, SysDataRowLimit> implements ISysDataRowLimitService {
    private final SysDataRowLimitMapper sysDataRowLimitMapper;
    private final ICacheService iCacheService;

    @Override
    public Set<SysDataRowLimit> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        return sysDataRowLimitMapper.getUserDataRowPerm(groupIdSet, postIdSet, roleIdSet);
    }

    @Override
    public boolean updateDataRowLimitStatus(String dataRowLimitId, String status) {
        boolean isUpdate = SqlHelper.retBool(sysDataRowLimitMapper.update(null, Wrappers.<SysDataRowLimit>lambdaUpdate().set(!StringUtils.isEmpty(status), SysDataRowLimit::getStatus, status).eq(SysDataRowLimit::getId, dataRowLimitId)));
        if (isUpdate) {
            iCacheService.cleanDataRowLimitCache(dataRowLimitId);
        }
        return isUpdate;
    }
}
