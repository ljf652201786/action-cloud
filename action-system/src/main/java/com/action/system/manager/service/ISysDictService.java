package com.action.system.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.entity.SysDict;

public interface ISysDictService extends BaseMpService<SysDict> {

    boolean checkDictCodeExist(String code);

    boolean updateInfo(SysDict sysDict);

    boolean disable(String dictId);

    boolean enable(String dictId);
}
