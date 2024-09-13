package com.action.system.bsup.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.bsup.struct.dto.ColumnAllocationDto;
import com.action.system.bsup.struct.entity.SysDataColumnLimit;
import com.action.system.bsup.struct.vo.SysDataColumnLimitVo;

import java.util.List;
import java.util.Set;

public interface ISysDataColumnLimitService extends BaseMpService<SysDataColumnLimit> {

    List<SysDataColumnLimitVo> getInfo(String dataId, String type, String contactId);

    Set<SysDataColumnLimit> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    void resetDataColumn(String type, String contactId, String dataId);

    boolean saveInfo(ColumnAllocationDto columnAllocationDto);
}
