package com.action.system.bsup.service.impl;

import com.action.system.bsup.struct.dto.ColumnAllocationDto;
import com.action.system.bsup.struct.entity.SysDataColumnLimit;
import com.action.system.bsup.mapper.SysDataColumnLimitMapper;
import com.action.system.bsup.service.ISysDataColumnLimitService;
import com.action.system.bsup.struct.vo.SysDataColumnLimitVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 数据列权限表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysDataColumnLimitServiceImpl extends ServiceImpl<SysDataColumnLimitMapper, SysDataColumnLimit> implements ISysDataColumnLimitService {
    private final SysDataColumnLimitMapper sysDataColumnLimitMapper;

    @Override
    public List<SysDataColumnLimitVo> getInfo(String dataId, String type, String contactId) {
        return this.selectListBy((e) -> e.list(this.getLambdaQueryWrapper().eq(SysDataColumnLimit::getDataId, dataId).eq(SysDataColumnLimit::getType, type).eq(SysDataColumnLimit::getContactId, contactId)),
                SysDataColumnLimitVo.class);
    }

    @Override
    public Set<SysDataColumnLimit> getUserDataColumnPerm(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet) {
        return sysDataColumnLimitMapper.getUserDataColumnPerm(groupIdSet, postIdSet, roleIdSet);
    }

    @Override
    public void resetDataColumn(String type, String contactId, String dataId) {
        this.remove(this.getLambdaQueryWrapper()
                .eq(SysDataColumnLimit::getType, type)
                .eq(SysDataColumnLimit::getContactId, contactId)
                .eq(SysDataColumnLimit::getDataId, dataId));
    }

    @Override
    public boolean saveInfo(ColumnAllocationDto columnAllocationDto) {
        resetDataColumn(columnAllocationDto.getType(), columnAllocationDto.getContactId(), columnAllocationDto.getDataId());
        if (!CollectionUtils.isEmpty(columnAllocationDto.getColumnDtoList())) {
            List<SysDataColumnLimit> sysDataColumnLimitList = columnAllocationDto.getColumnDtoList().stream().map(column -> {
                return new SysDataColumnLimit(columnAllocationDto.getDataId(), columnAllocationDto.getType(), columnAllocationDto.getContactId(), column.getField(), column.getFieldDesc());
            }).collect(Collectors.toList());
            return this.saveBatch(sysDataColumnLimitList);
        }
        return true;
    }


}
