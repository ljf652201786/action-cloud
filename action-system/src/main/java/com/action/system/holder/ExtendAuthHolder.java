package com.action.system.holder;

import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.security.holder.IAuthHolder;
import com.action.system.manager.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Description: 扩展认证
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/23
 */
@Service
@RequiredArgsConstructor
public class ExtendAuthHolder implements IAuthHolder {
    private final ISysMenuService iSysMenuService;

    @Override
    public Set<? extends BaseSecurityMenu> getSysPermission() {
        return iSysMenuService.getSysPermission();
    }
}
