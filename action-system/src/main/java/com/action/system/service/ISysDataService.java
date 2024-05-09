package com.action.system.service;

import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.system.entity.SysData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ISysDataService extends IService<SysData> {
    List<SysData> buildDataTreeSelect();

    Set<DataRowFilterStruct> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    Map<String, Set<String>> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);
}
