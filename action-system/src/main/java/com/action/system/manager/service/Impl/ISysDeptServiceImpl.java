package com.action.system.manager.service.Impl;

import com.action.common.core.enums.NodeTypeEnum;
import com.action.common.enums.StatusType;
import com.action.system.bsup.service.ISysScopeService;
import com.action.system.manager.struct.entity.SysDept;
import com.action.system.manager.struct.entity.SysPost;
import com.action.system.manager.mapper.SysDeptMapper;
import com.action.system.manager.mapper.SysPostMapper;
import com.action.system.manager.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 部门表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {
    private final SysDeptMapper sysDeptMapper;
    private final SysPostMapper sysPostMapper;
    private final ISysScopeService iSysScopeService;


    @Override
    public boolean checkDeptCodeExist(String code) {
        return this.getOneOpt(this.getLambdaQueryWrapper().eq(SysDept::getDeptCode, code)).isPresent();
    }

    @Override
    public boolean updateInfo(SysDept sysDept) {
        return this.update(sysDept, this.getLambdaUpdateWrapper().eq(SysDept::getId, sysDept.getId()).eq(SysDept::getDeptCode, sysDept.getDeptCode()));
    }

    @Override
    public boolean disable(String deptId) {
        boolean isDisable = this.update(this.getLambdaUpdateWrapper().set(SysDept::getStatus, StatusType.DISABLED.getStatus()).eq(SysDept::getId, deptId));
        if (isDisable) {
            //更新scope表
            iSysScopeService.updateDeptStatus(deptId, StatusType.DISABLED.getStatus());
        }
        return isDisable;
    }

    @Override
    public boolean enable(String deptId) {
        boolean isEnable = this.update(this.getLambdaUpdateWrapper().set(SysDept::getStatus, StatusType.ENABLE.getStatus()).eq(SysDept::getId, deptId));
        if (isEnable) {
            //更新scope表
            iSysScopeService.updateDeptStatus(deptId, StatusType.ENABLE.getStatus());
        }
        return isEnable;
    }

    @Override
    public List<SysDept> buildDeptPostTreeSelect() {
        List<SysDept> sysDeptList = sysDeptMapper.selectList(this.getLambdaUpdateWrapper().eq(SysDept::getStatus, StatusType.ENABLE.getStatus()));
        List<SysPost> sysPostList = sysPostMapper.selectList(this.getLambdaUpdateWrapper(new SysPost()).eq(SysPost::getStatus, StatusType.ENABLE.getStatus()));
        if (CollectionUtils.isEmpty(sysDeptList)) {
            return List.of();
        }
        List<SysDept> parentDeptList = sysDeptList.stream().filter(dept -> dept.getParentId().equals(NodeTypeEnum.PARENT.getType())).collect(Collectors.toList());
        parentDeptList.stream().forEach(parentDept -> {
            List<SysDept> childrenDeptList = new ArrayList<>();
            parentDept.setChildrenList(childrenDeptList);
            List<SysPost> postList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(sysPostList)) {
                sysPostList.stream().forEach(post -> {
                    if (parentDept.getId().equals(post.getDeptId())) {
                        postList.add(post);
                    }
                });
            }
            parentDept.setPostList(postList);
            this.buildDeptPostTree(sysDeptList, sysPostList, childrenDeptList, parentDept.getId());
        });
        return parentDeptList;
    }

    private void buildDeptPostTree(List<SysDept> depts, List<SysPost> posts, List<SysDept> childrenDeptList, String parentId) {
        depts.stream().forEach(dept -> {
            if (dept.getParentId().equals(parentId)) {
                List<SysDept> cdl = new ArrayList<>();
                dept.setChildrenList(cdl);
                List<SysPost> postList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(posts)) {
                    posts.stream().forEach(post -> {
                        if (dept.getId().equals(post.getDeptId())) {
                            postList.add(post);
                        }
                    });
                }
                dept.setPostList(postList);
                childrenDeptList.add(dept);
                this.buildDeptPostTree(depts, posts, cdl, dept.getId());
            }
        });
    }

}
