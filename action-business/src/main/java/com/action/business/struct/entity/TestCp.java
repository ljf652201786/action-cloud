package com.action.business.struct.entity;

import com.action.common.mybatisplus.extend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: test对象
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("biz_test_cp")
public class TestCp extends BaseEntity {
    @TableId("id")
    private String id;
    @TableField("age")
    private String age;
    @TableField("test_id")
    private String TestId;
    @TableField("re")
    private String re;
}
