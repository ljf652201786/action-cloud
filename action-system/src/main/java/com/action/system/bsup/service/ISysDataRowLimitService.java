package com.action.system.bsup.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.bsup.struct.entity.SysDataRowLimit;
import com.action.system.bsup.struct.vo.SysDataRowLimitVo;

import java.util.List;
import java.util.Set;

public interface ISysDataRowLimitService extends BaseMpService<SysDataRowLimit> {

    List<SysDataRowLimitVo> getInfo(String dataId, String type, String contactId);

    boolean saveInfo(SysDataRowLimit sysDataRowLimit);

    boolean updateInfo(SysDataRowLimit sysDataRowLimit);

    boolean disable(String id);

    boolean enable(String id);

    Set<SysDataRowLimit> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);
}
