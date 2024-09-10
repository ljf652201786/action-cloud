package com.action.third.service.manager.struct.entity;

import com.action.common.mybatisplus.extend.annotation.Condition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 第三方平台回调信息
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("thrid_platform_callback")
public class ThirdPlatformCallBack {
    @TableId("id")
    private String id;
    @TableField("content_id")
    private String contentId;
    @TableField("camera_name")
    private String cameraName;
    @Condition(value = Condition.ConditionTypeEnums.IN,
            convert = Condition.ConvertTypeEnums.COMMA_SPLIT)
    @TableField("algorithm_id")
    private String algorithmId;
    @TableField("algorithm_name")
    private String algorithmName;
    @TableField("degree")
    private String degree;
    @Condition(
            value = Condition.ConditionTypeEnums.BETWEEN,
            order = Condition.OrderTypeEnums.DESC
    )
    @TableField("timestamp")
    private String timestamp;
    @TableField("src_pic_name")
    private String srcPicName;
    @TableField("src_pic_url")
    private String srcPicUrl;
    @TableField("alarm_pic_name")
    private String alarmPicName;
    @TableField("alarm_pic_url")
    private String alarmPicUrl;
    @TableField("video_name")
    private String videoName;
    @TableField("video_url")
    private String videoUrl;
}
