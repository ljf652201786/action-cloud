package com.action.system.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.entity.SysGroup;

public interface ISysGroupService extends BaseMpService<SysGroup> {

    boolean checkGroupCodeExist(String code);

    boolean updateInfo(SysGroup sysGroup);

    boolean disable(String id);
    
    boolean enable(String id);
}
