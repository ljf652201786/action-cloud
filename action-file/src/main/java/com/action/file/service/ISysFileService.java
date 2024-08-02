package com.action.file.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.file.entity.SysFile;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISysFileService extends BaseMpService<SysFile> {
    List<String> upload(MultipartFile[] multipartFile, Integer index);

    void downloadFile(List<String> ids, boolean iszip, HttpServletResponse response);

    boolean pseudoDeletionBatchByIds(List<String> ids);

    boolean recoveryBaseFilesByIds(List<String> ids);

    boolean removeBatchFilesByIds(List<String> ids);
}
