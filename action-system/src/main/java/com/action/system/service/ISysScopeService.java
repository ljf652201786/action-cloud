package com.action.system.service;

import com.action.system.struct.entity.SysScope;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISysScopeService extends IService<SysScope> {
    List<SysScope> getSysScopeByUserId(String userId);

    Long deptNum(String deptId);

    Long postNum(String deptId);

    boolean updateDeptStatus(String deptId, String status);

    boolean updatePostStatus(String postId, String status);
}
