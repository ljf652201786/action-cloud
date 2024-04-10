package com.action.system.service;

import com.action.system.entity.SysData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ISysDataService extends IService<SysData> {
    List<SysData> buildDataTreeSelect();
}
