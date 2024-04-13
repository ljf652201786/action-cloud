package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysData;
import com.action.system.entity.SysDept;
import com.action.system.enums.NodeType;
import com.action.system.mapper.SysDataMapper;
import com.action.system.service.ISysDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 数据权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysDataServiceImpl extends ServiceImpl<SysDataMapper, SysData> implements ISysDataService {
    @Resource
    private SysDataMapper sysDataMapper;

    @Override
    public List<SysData> buildDataTreeSelect() {
        List<SysData> sysDataList = sysDataMapper.selectList(new QueryWrapper<SysData>().eq("status", UseType.ENABLE.getStatus()));
        if (CollectionUtils.isEmpty(sysDataList)) {
            return List.of();
        }
        List<SysData> parentDataList = sysDataList.stream().filter(data -> data.getParentId().equals(NodeType.PARENT.getType())).collect(Collectors.toList());
        parentDataList.stream().forEach(parentData -> {
            List<SysData> childrenDataList = new ArrayList<>();
            parentData.setChildrenDataList(childrenDataList);
            this.buildDataTree(sysDataList, childrenDataList, parentData.getId());
        });
        return parentDataList;
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
