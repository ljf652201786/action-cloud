package com.action.system.service.Impl;

import com.action.system.struct.entity.SysDict;
import com.action.system.mapper.SysDictMapper;
import com.action.system.service.ISysDictService;
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

}
