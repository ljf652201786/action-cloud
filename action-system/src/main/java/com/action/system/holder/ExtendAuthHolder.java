package com.action.system.holder;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.security.holder.IAuthHolder;
import com.action.system.bsup.service.ICacheService;
import com.action.system.manager.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description: 扩展认证
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/23
 */
@Service
@RequiredArgsConstructor
public class ExtendAuthHolder implements IAuthHolder {
    private final ISysMenuService iSysMenuService;
    private final ICacheService iCacheService;

    @Override
    public List<? extends BaseSecurityMenu> getSysPermission() {
        List<? extends BaseSecurityMenu> sysPermission = iCacheService.getSysMenuCache();
        if (CollectionUtils.isEmpty(sysPermission)) {
            sysPermission = iSysMenuService.getSysPermission();
            iCacheService.setSysMenuCache(sysPermission);
            return sysPermission;
        }
        return sysPermission;
    }
}
