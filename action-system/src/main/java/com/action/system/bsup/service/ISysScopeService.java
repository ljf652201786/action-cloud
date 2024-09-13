package com.action.system.bsup.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.bsup.struct.entity.SysScope;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

public interface ISysScopeService extends BaseMpService<SysScope> {

    Set<String> getPostIdSetAndCache(String userId, String username);

    List<SysScope> getSysScopeByUserId(String userId);

    Set<String> saveBatchByUserId(String userId, List<SysScope> scopeList);

    Set<String> updateBatchByUserId(String userId, List<SysScope> scopeList);

    Long deptNum(String deptId);

    Long postNum(String postId);

    boolean updateDeptStatus(String deptId, String status);

    boolean updatePostStatus(String postId, String status);
}
