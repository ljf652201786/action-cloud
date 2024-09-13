package com.action.system.bsup.service.impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.struct.entity.SysDataRowLimit;
import com.action.system.bsup.mapper.SysDataRowLimitMapper;
import com.action.system.bsup.service.ICacheService;
import com.action.system.bsup.service.ISysDataRowLimitService;
import com.action.system.bsup.struct.vo.SysDataRowLimitVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<SysDataRowLimitVo> getInfo(String dataId, String type, String contactId) {
        return this.selectListBy((e) -> e.list(this.getLambdaQueryWrapper().eq(SysDataRowLimit::getDataId, dataId).eq(SysDataRowLimit::getType, type).eq(SysDataRowLimit::getContactId, contactId)),
                SysDataRowLimitVo.class);
    }

    @Override
    public boolean saveInfo(SysDataRowLimit sysDataRowLimit) {
        sysDataRowLimit.setStatus(StatusType.ENABLE.getStatus());
        sysDataRowLimit.setRelation("and");
        return this.save(sysDataRowLimit);
    }

    @Override
    public boolean updateInfo(SysDataRowLimit sysDataRowLimit) {
        return this.update(sysDataRowLimit, this.getLambdaUpdateWrapper().set(SysDataRowLimit::getRelation, "and").eq(SysDataRowLimit::getId, sysDataRowLimit.getId()));
    }

    @Override
    public boolean disable(String id) {
        boolean isDisable = this.update(this.getLambdaUpdateWrapper().set(SysDataRowLimit::getStatus, StatusType.DISABLED.getStatus()).eq(SysDataRowLimit::getId, id));
        if (isDisable) {
            iCacheService.cleanDataRowLimitCache(id);
        }
        return isDisable;
    }

    @Override
    public boolean enable(String id) {
        boolean isEnable = this.update(this.getLambdaUpdateWrapper().set(SysDataRowLimit::getStatus, StatusType.ENABLE.getStatus()).eq(SysDataRowLimit::getId, id));
        if (isEnable) {
            iCacheService.cleanDataRowLimitCache(id);
        }
        return isEnable;
    }

    @Override
    public Set<SysDataRowLimit> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        return sysDataRowLimitMapper.getUserDataRowPerm(groupIdSet, postIdSet, roleIdSet);
    }
}
