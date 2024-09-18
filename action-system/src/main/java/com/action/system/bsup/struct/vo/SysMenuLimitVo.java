package com.action.system.bsup.struct.vo;

import lombok.Data;

/**
 * @Description: 目录权限Vo
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/06
 */
@Data
public class SysMenuLimitVo {
    private String id;
    private String type;
    private String contactId;
    private String menuId;
    private String status;
}
