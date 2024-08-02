package com.action.system.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.struct.entity.SysDept;

import java.util.List;

public interface ISysDeptService extends BaseMpService<SysDept> {

    List<SysDept> buildDeptPostTreeSelect();
}
