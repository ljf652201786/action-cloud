package com.action.system.service;

import com.action.system.entity.SysScope;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISysScopeService extends IService<SysScope> {
    List<SysScope> getSysScopeByUserId(String userId);
}
