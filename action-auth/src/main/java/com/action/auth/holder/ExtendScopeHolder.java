/*
package com.action.auth.holder;


import com.action.common.mybatisplus.extend.IDataScopeHolder;
import com.action.common.mybatisplus.extend.filter.datapermission.DataRowFilterStruct;
import com.action.common.mybatisplus.extend.filter.dytablename.TableStruct;
import org.springframework.stereotype.Service;

import java.util.*;

*/
/**
 * @Description: 扩展数据权限接口
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/03/29
 *//*

@Service
public class ExtendScopeHolder implements IDataScopeHolder {
    */
/*
     * 数据列权限配置
     * *//*

    @Override
    public Map<String, Set<String>> getDataColumnScope() {
        Map<String, Set<String>> queryDataScopeMap = new HashMap<String, Set<String>>() {{
            Set<String> strings = new HashSet<>();
            strings.add("user_name");
            strings.add("id");
            put("sys_user", strings);
        }};
        return queryDataScopeMap;
    }

    */
/*
     * 数据行权限配置
     * *//*

    @Override
    public List<DataRowFilterStruct> getDataRowScope() {
        List<DataRowFilterStruct> dataRowFilterStructs = new ArrayList<>();
        DataRowFilterStruct dataRowFilterStruct = new DataRowFilterStruct();
        dataRowFilterStruct.setTableName("sys_user");
        dataRowFilterStruct.setTableField("sex");
        dataRowFilterStruct.setRelation("and");
        dataRowFilterStruct.setCondition("=");
        dataRowFilterStruct.setVal("1");
        dataRowFilterStructs.add(dataRowFilterStruct);
        return dataRowFilterStructs;
    }

    */
/*
     * 动态表名
     * *//*

    @Override
    public Map<String, TableStruct> getTableStruct() {
        Map<String, TableStruct> map = new HashMap<>();
        map.put("sys_user", null);
        return map;
    }
}
*/
