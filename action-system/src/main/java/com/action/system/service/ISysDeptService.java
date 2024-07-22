package com.action.system.service;

import com.action.system.struct.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISysDeptService extends IService<SysDept> {

    List<SysDept> buildDeptPostTreeSelect();
}
