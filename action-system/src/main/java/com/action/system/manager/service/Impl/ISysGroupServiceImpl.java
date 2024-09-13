package com.action.system.manager.service.Impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.service.ISysUserGroupService;
import com.action.system.manager.struct.entity.SysGroup;
import com.action.system.manager.mapper.SysGroupMapper;
import com.action.system.manager.service.ISysGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户组
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements ISysGroupService {
    private final SysGroupMapper sysGroupMapper;
    private final ISysUserGroupService iSysUserGroupService;

    @Override
    public boolean checkGroupCodeExist(String code) {
        return this.getOneOpt(this.getLambdaQueryWrapper().eq(SysGroup::getGroupCode, code)).isPresent();
    }

    @Override
    public boolean updateInfo(SysGroup sysGroup) {
        return this.update(sysGroup, this.getLambdaUpdateWrapper().eq(SysGroup::getId, sysGroup.getId()).eq(SysGroup::getGroupCode, sysGroup.getGroupCode()));
    }

    @Override
    public boolean disable(String id) {
        boolean isDisable = this.update(this.getLambdaUpdateWrapper().set(SysGroup::getStatus, StatusType.DISABLED.getStatus()).eq(SysGroup::getId, id));
        if (isDisable) {
            iSysUserGroupService.updateGroupStatus(id, StatusType.DISABLED.getStatus());
        }
        return isDisable;
    }

    @Override
    public boolean enable(String id) {
        boolean isEnable = this.update(this.getLambdaUpdateWrapper().set(SysGroup::getStatus, StatusType.ENABLE.getStatus()).eq(SysGroup::getId, id));
        if (isEnable) {
            iSysUserGroupService.updateGroupStatus(id, StatusType.ENABLE.getStatus());
        }
        return isEnable;
    }
}
