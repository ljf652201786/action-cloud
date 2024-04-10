package com.action.system.service.Impl;

import com.action.system.entity.SysDictDetail;
import com.action.system.mapper.SysDictDetailMapper;
import com.action.system.service.ISysDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 字典详情表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements ISysDictDetailService {
    @Resource
    private SysDictDetailMapper sysDictDetailMapper;

}
