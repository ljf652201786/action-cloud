package com.action.file.service;

import com.action.file.entity.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISysFileService extends IService<SysFile> {
    List<String> upload(MultipartFile[] multipartFile, Integer index);

    void downloadFile(List<String> ids, boolean iszip, HttpServletResponse response);

    boolean pseudoDeletionBatchByIds(List<String> ids);

    boolean recoveryBaseFilesByIds(List<String> ids);

    boolean removeBatchFilesByIds(List<String> ids);
}
