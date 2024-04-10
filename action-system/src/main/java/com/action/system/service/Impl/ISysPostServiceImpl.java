package com.action.system.service.Impl;

import com.action.system.entity.SysPost;
import com.action.system.mapper.SysPostMapper;
import com.action.system.service.ISysPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 岗位表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
public class ISysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements ISysPostService {
    @Resource
    private SysPostMapper sysPostMapper;

}
