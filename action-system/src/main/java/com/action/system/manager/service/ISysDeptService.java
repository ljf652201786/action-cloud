package com.action.system.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.entity.SysDept;

import java.util.List;

public interface ISysDeptService extends BaseMpService<SysDept> {
    
    boolean checkDeptCodeExist(String code);

    boolean updateInfo(SysDept sysDept);

    boolean disable(String deptId);

    boolean enable(String deptId);

    List<SysDept> buildDeptPostTreeSelect();
}
