package com.action.system.bsup.service;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.bsup.struct.vo.SysMenuLimitVo;
import com.action.system.manager.struct.dto.PermAllocationDto;
import com.action.system.manager.struct.entity.SysMenu;
import com.action.system.bsup.struct.entity.SysMenuLimit;

import java.util.List;
import java.util.Set;


public interface ISysMenuLimitService extends BaseMpService<SysMenuLimit> {

    Set<SysMenu> getSysMenuByScope(Set<String> deptIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    void resetMenu(String type, String contactId);

    List<BaseSecurityMenu> getBaseSecurityMenuByScope(Set<String> groupIdSet, Set<String> postIdSet, Set<String> roleIdSet);

    boolean allocationMenu(PermAllocationDto permAllocationDto);

    List<SysMenuLimitVo> getAllocationMenu(String type, String contactId);
}
