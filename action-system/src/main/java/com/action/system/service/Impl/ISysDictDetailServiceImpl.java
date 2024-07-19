package com.action.system.service.Impl;

import com.action.system.struct.entity.SysDictDetail;
import com.action.system.mapper.SysDictDetailMapper;
import com.action.system.service.ISysDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 字典详情表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements ISysDictDetailService {
    private final SysDictDetailMapper sysDictDetailMapper;

}
