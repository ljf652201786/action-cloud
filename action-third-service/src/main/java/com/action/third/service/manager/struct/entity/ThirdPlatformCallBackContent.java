package com.action.third.service.manager.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 第三方平台回调内容信息
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("thrid_platform_callback_content")
public class ThirdPlatformCallBackContent extends BaseEntity {
    @TableId("id")
    private String id;
    @TableField("content")
    private String content;
}
