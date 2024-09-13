package com.action.system.manager.service.Impl;

import com.action.common.core.enums.NodeTypeEnum;
import com.action.common.enums.StatusType;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.system.enums.DataType;
import com.action.system.manager.service.ISysDataService;
import com.action.system.manager.struct.entity.SysData;
import com.action.system.bsup.struct.entity.SysDataColumnLimit;
import com.action.system.bsup.struct.entity.SysDataRowLimit;
import com.action.system.manager.mapper.SysDataMapper;
import com.action.system.bsup.service.ISysDataColumnLimitService;
import com.action.system.bsup.service.ISysDataRowLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 数据权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysDataServiceImpl extends ServiceImpl<SysDataMapper, SysData> implements ISysDataService {
    private final SysDataMapper sysDataMapper;
    private final ISysDataRowLimitService iSysDataRowLimitService;
    private final ISysDataColumnLimitService iSysDataColumnLimitService;

    @Override
    public List<SysData> buildDataTreeSelect() {
        List<SysData> sysDataList = this.list(this.getLambdaQueryWrapper().eq(SysData::getStatus, StatusType.ENABLE.getStatus()));
        if (CollectionUtils.isEmpty(sysDataList)) {
            return List.of();
        }
        List<SysData> parentDataList = sysDataList.stream().filter(data -> data.getParentId().equals(NodeTypeEnum.PARENT.getType())).collect(Collectors.toList());
        parentDataList.stream().forEach(parentData -> {
            List<SysData> childrenDataList = new ArrayList<>();
            parentData.setChildrenDataList(childrenDataList);
            this.buildDataTree(sysDataList, childrenDataList, parentData.getId());
        });
        return parentDataList;
    }

    @Override
    public boolean checkDataCodeExist(String code) {
        return this.getOneOpt(this.getLambdaQueryWrapper().eq(SysData::getDataCode, code)).isPresent();
    }

    @Override
    public boolean updateInfo(SysData sysData) {
        if (DataType.TABLE.getType().equals(sysData.getType())) {
            return this.update(sysData, this.getLambdaUpdateWrapper().eq(SysData::getId, sysData.getId()).eq(SysData::getDataCode, sysData.getDataCode()));
        }
        return this.updateById(sysData);
    }

    @Override
    public Set<DataRowFilterStruct> getUserDataRowPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        Set<SysDataRowLimit> userDataRowPermSet = iSysDataRowLimitService.getUserDataRowPerm(groupIdSet, postIdSet, roleIdSet);
        Set<DataRowFilterStruct> dataRowFilterStructSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(userDataRowPermSet)) {
            dataRowFilterStructSet = userDataRowPermSet.stream().map(dataRowLimit -> {
                return new DataRowFilterStruct(dataRowLimit.getId(), dataRowLimit.getTableName(), dataRowLimit.getLimitField(), "and", dataRowLimit.getLimitCondition(), dataRowLimit.getVal());
            }).collect(Collectors.toSet());
        }
        return dataRowFilterStructSet;
    }

    @Override
    public Map<String, Set<String>> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        Set<SysDataColumnLimit> userDataColumnPermSet = iSysDataColumnLimitService.getUserDataColumnPerm(groupIdSet, postIdSet, roleIdSet);
        Map<String, Set<String>> dataColumnFilterMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(userDataColumnPermSet)) {
            dataColumnFilterMap = userDataColumnPermSet.stream()
                    .filter(sysDataColumnLimit -> StringUtils.isNotEmpty(sysDataColumnLimit.getTableName()) || StringUtils.isNotEmpty(sysDataColumnLimit.getLimitField()))
                    .collect(
                            Collectors.groupingBy(po -> po.getTableName(),
                                    Collectors.mapping(po -> po.getLimitField(), Collectors.toSet())
                            )
                    );
        }
        return dataColumnFilterMap;
    }

    private void buildDataTree(List<SysData> datas, List<SysData> childrenDeptList, String parentId) {
        datas.stream().forEach(dept -> {
            if (dept.getParentId().equals(parentId)) {
                List<SysData> childrenDataList1 = new ArrayList<>();
                dept.setChildrenDataList(childrenDataList1);
                childrenDeptList.add(dept);
                this.buildDataTree(datas, childrenDataList1, dept.getId());
            }
        });
    }
}
