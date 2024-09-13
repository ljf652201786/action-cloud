package com.action.system.manager.service.Impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.service.ISysScopeService;
import com.action.system.manager.service.ISysPostService;
import com.action.system.manager.struct.entity.SysPost;
import com.action.system.manager.mapper.SysPostMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Description: 岗位表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements ISysPostService {
    private final SysPostMapper sysPostMapper;
    private final ISysScopeService iSysScopeService;

    @Override
    public boolean checkPostCodeExist(String code) {
        return this.getOneOpt(this.getLambdaQueryWrapper().eq(SysPost::getPostCode, code)).isPresent();
    }

    @Override
    public boolean updateInfo(SysPost sysPost) {
        return this.update(sysPost, this.getLambdaUpdateWrapper().eq(SysPost::getId, sysPost.getId()).eq(SysPost::getPostCode, sysPost.getPostCode()));

    }

    @Override
    public boolean disable(String postId) {
        boolean isDisable = this.update(this.getLambdaUpdateWrapper().set(SysPost::getStatus, StatusType.DISABLED.getStatus()).eq(SysPost::getId, postId));
        if (isDisable) {
            //更新scope表
            iSysScopeService.updatePostStatus(postId, StatusType.DISABLED.getStatus());
        }
        return isDisable;
    }

    @Override
    public boolean enable(String postId) {
        boolean isDisable = this.update(this.getLambdaUpdateWrapper().set(SysPost::getStatus, StatusType.ENABLE.getStatus()).eq(SysPost::getId, postId));
        if (isDisable) {
            //更新scope表
            iSysScopeService.updatePostStatus(postId, StatusType.ENABLE.getStatus());
        }
        return isDisable;
    }
}
