package com.action.system.service.Impl;

import com.action.common.enums.UseType;
import com.action.system.entity.SysDept;
import com.action.system.entity.SysPost;
import com.action.system.enums.NodeType;
import com.action.system.mapper.SysDeptMapper;
import com.action.system.mapper.SysPostMapper;
import com.action.system.service.ISysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<SysDept> buildDeptTreeSelect(SysDept sysDept) {
        List<SysDept> sysDeptList = this.list();
        if (CollectionUtils.isEmpty(sysDeptList)) {
            return List.of();
        }
        List<SysDept> parentDeptList = sysDeptList.stream().filter(dept -> dept.getParentId().equals(NodeType.PARENT.getType())).collect(Collectors.toList());
        parentDeptList.stream().forEach(parentDept -> {
            List<SysDept> childrenDeptList = new ArrayList<>();
            parentDept.setChildrenList(childrenDeptList);
            this.buildDeptTree(sysDeptList, childrenDeptList, parentDept.getId());
        });
        return parentDeptList;
    }

    @Override
    public List<SysDept> buildDeptPostTreeSelect() {
        List<SysDept> sysDeptList = sysDeptMapper.selectList(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getStatus, UseType.ENABLE.getStatus()));
        List<SysPost> sysPostList = sysPostMapper.selectList(Wrappers.<SysPost>lambdaQuery().eq(SysPost::getStatus, UseType.ENABLE.getStatus()));
        if (CollectionUtils.isEmpty(sysDeptList)) {
            return List.of();
        }
        List<SysDept> parentDeptList = sysDeptList.stream().filter(dept -> dept.getParentId().equals(NodeType.PARENT.getType())).collect(Collectors.toList());
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

    private void buildDeptTree(List<SysDept> depts, List<SysDept> childrenDeptList, String parentId) {
        depts.stream().forEach(dept -> {
            if (dept.getParentId().equals(parentId)) {
                List<SysDept> childrenDeptList1 = new ArrayList<>();
                dept.setChildrenList(childrenDeptList1);
                childrenDeptList.add(dept);
                this.buildDeptTree(depts, childrenDeptList1, dept.getId());
            }
        });
    }

    private void buildDeptPostTree(List<SysDept> depts, List<SysPost> posts, List<SysDept> childrenDeptList, String parentId) {
        depts.stream().forEach(dept -> {
            if (dept.getParentId().equals(parentId)) {
                List<SysDept> childrenDeptList1 = new ArrayList<>();
                dept.setChildrenList(childrenDeptList1);
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
                this.buildDeptPostTree(depts, posts, childrenDeptList1, dept.getId());
            }
        });
    }
}
