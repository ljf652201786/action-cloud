package com.action.system.manager.service.Impl;

import com.action.system.manager.service.ISysDictService;
import com.action.system.manager.struct.entity.SysDict;
import com.action.system.manager.mapper.SysDictMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @Description: 字典表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {
    private final SysDictMapper sysDictMapper;

    @Override
    public boolean checkDictCodeExist(String code) {
        return this.getOneOpt(this.getLambdaQueryWrapper().eq(SysDict::getDictCode, code)).isPresent();
    }

    @Override
    public boolean updateInfo(SysDict sysDict) {
        return this.update(sysDict, this.getLambdaUpdateWrapper().eq(SysDict::getId, sysDict.getId()).eq(SysDict::getDictCode, sysDict.getDictCode()));
    }
}
