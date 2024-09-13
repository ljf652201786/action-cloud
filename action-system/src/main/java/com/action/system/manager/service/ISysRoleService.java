package com.action.system.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.entity.SysRole;

public interface ISysRoleService extends BaseMpService<SysRole> {

    boolean checkRoleCodeExist(String code);

    boolean updateInfo(SysRole sysRole);

    boolean setDefault(String id, boolean isDefault);

    boolean disable(String id);

    boolean enable(String id);
}
