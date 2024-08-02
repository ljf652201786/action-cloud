package com.action.system.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.system.struct.entity.SysData;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ISysDataService extends BaseMpService<SysData> {
    List<SysData> buildDataTreeSelect();

    Set<DataRowFilterStruct> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    Map<String, Set<String>> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);
}
