package com.action.system.service.Impl;

import com.action.system.entity.SysGroup;
import com.action.system.mapper.SysGroupMapper;
import com.action.system.service.ISysGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户组
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements ISysGroupService {
    private final SysGroupMapper sysGroupMapper;

}
