package com.action.system.holder;

import com.action.common.mybatisplus.extend.IDataScopeHolder;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.common.mybatisplus.extend.filter.dytablename.TableStruct;
import com.action.system.service.ICacheService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 扩展数据权限接口
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/03/29
 */
@Service
public class ExtendScopeHolder implements IDataScopeHolder {
    @Resource
    private ICacheService iCacheService;

    /*
     * 数据列权限配置
     * */
    @Override
    public Map<String, Set<String>> getDataColumnScope() {
        return iCacheService.getUserDataPermColumnCache();
    }

    /*
     * 数据行权限配置
     * */
    @Override
    public Set<DataRowFilterStruct> getDataRowScope() {
        return iCacheService.getUserDataPermRowCache();
    }

    /*
     * 动态表名
     * */
    @Override
    public Map<String, TableStruct> getTableStruct() {
        Map<String, TableStruct> map = new HashMap<>();
//        map.put("sys_user", null);
        return map;
    }
}
