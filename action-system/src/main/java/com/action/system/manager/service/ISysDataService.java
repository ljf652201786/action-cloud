package com.action.system.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.system.manager.struct.entity.SysData;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ISysDataService extends BaseMpService<SysData> {

    List<SysData> buildDataTreeSelect();

    boolean checkDataCodeExist(String code);

    boolean updateInfo(SysData sysData);

    boolean disable(String dataId);

    boolean enable(String dataId);

    Set<DataRowFilterStruct> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    Map<String, Set<String>> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);
}
