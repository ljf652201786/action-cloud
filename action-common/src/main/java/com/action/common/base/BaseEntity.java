package com.action.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 基础基类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1618606193003634725L;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}
