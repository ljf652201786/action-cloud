package com.action.system.manager.struct.vo;

import com.action.common.mybatisplus.extend.annotation.CorrelationField;
import com.action.common.mybatisplus.extend.annotation.CorrelationTable;
import com.action.common.mybatisplus.extend.annotation.CorrelationTables;
import com.action.system.bsup.struct.entity.SysScope;
import com.action.system.manager.struct.entity.SysDept;
import com.action.system.sconf.struct.entity.SysApp;
import com.action.system.sconf.struct.entity.SysMultiTenant;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @Description: 用户Vo
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
public class UserVo {
    private String id;
    @CorrelationTables({@CorrelationTable(value = SysMultiTenant.class, column = "tenant_id")})
    private String tenantId;
    @CorrelationField(table = SysMultiTenant.class, column = "tenant_name")
    private String tenantName;
    private String avatar;
    private String username;
    private String nickName;
    private String email;
    private String phone;
    private String sex;
    private String status;
    @TableField(exist = false)
    private List<String> groupList;
    @TableField(exist = false)
    private List<String> roleList;
    @TableField(exist = false)
    private List<SysScope> sysScopeList;
}
