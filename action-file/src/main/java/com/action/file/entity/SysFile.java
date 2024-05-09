package com.action.file.entity;

import com.action.common.base.BaseEntity;
import com.action.common.enums.DelType;
import com.action.common.file.entity.FileStruct;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 附件表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_file")
public class SysFile extends BaseEntity {
    @TableId(value = "id")
    private String id;
    @TableField("file_original_name")
    private String fileOriginalName;
    @TableField("file_name")
    private String fileName;
    @TableField("file_type")
    private String fileType;
    @TableField("file_path")
    private String filePath;
    @TableField("file_url")
    private String fileUrl;
    @TableField("file_size")
    private Long fileSize;
    @TableField("upload_user_id")
    private String uploadUserId;
    @TableField("upload_user_name")
    private String uploadUserName;
    @TableField("del_flag")
    private String delFlag;

    public static SysFile of(FileStruct fileStruct) {
        SysFile sysFile = new SysFile();
        MultipartFile multipartFile = fileStruct.getMultipartFile();
        sysFile.setFileOriginalName(multipartFile.getOriginalFilename());
        sysFile.setFileName(fileStruct.getFileName());
        sysFile.setFileType(multipartFile.getContentType());
        sysFile.setFilePath(fileStruct.getPath());
        sysFile.setFileSize(multipartFile.getSize());
        sysFile.setDelFlag(DelType.EXIST.getStatus());
        return sysFile;
    }
}
