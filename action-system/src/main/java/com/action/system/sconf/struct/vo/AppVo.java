package com.action.system.sconf.struct.vo;

import com.action.common.mybatisplus.extend.annotation.CorrelationField;
import com.action.common.mybatisplus.extend.annotation.CorrelationTable;
import com.action.common.mybatisplus.extend.annotation.CorrelationTables;
import com.action.system.manager.struct.entity.SysUser;
import lombok.Data;

/**
 * @Description: 应用Vo
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
public class AppVo {
    private String id;
    private String appName;
    private String appId;
    private String appSecret;
    @CorrelationTables({@CorrelationTable(value = SysUser.class)})
    private String userId;
    @CorrelationField(table = SysUser.class, column = "user_name")
    private String username;
    @CorrelationField(table = SysUser.class, column = "nick_name")
    private String nickName;
    private String status;
    private String remark;
    private String createTime;
}
