package com.action.file.service.Impl;

import com.action.common.core.constants.StringPool;
import com.action.common.enums.DelType;
import com.action.common.file.entity.FileStruct;
import com.action.common.file.properties.FileProperties;
import com.action.common.file.utils.ActionFileUtils;
import com.action.common.security.util.SecurityUtils;
import com.action.file.entity.SysFile;
import com.action.file.mapper.SysFileMapper;
import com.action.file.service.ISysFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Description: 附件表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/08
 */
@Service
public class ISysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements ISysFileService {
    @Resource
    private SysFileMapper sysFileMapper;
    @Resource
    private FileProperties fileProperties;

    @Override
    public List<String> upload(MultipartFile[] multipartFiles, Integer index) {
        List<String> urlList = new ArrayList<>();
        try {
            if (Objects.isNull(index)) {
                index = 0;
            }
            List<FileProperties.Resource> resources = fileProperties.getResources();
            if (CollectionUtils.isEmpty(resources) || resources.size() < index) {
                return urlList;
            }
            String userName = SecurityUtils.getUserName();
            FileProperties.Resource resource = resources.get(index);
            String localPath = this.getDefaultPath(resource);
            String pathPattern = this.getDefaultPathPattern(resource);
            Future<List<FileStruct>> future = ActionFileUtils.build().upload(multipartFiles, localPath);

            if (future.isDone() && future.get().size() > 0) {
                List<SysFile> sysFileList = future.get().stream().map(fileStruct -> {
                    String url = pathPattern + fileStruct.getFileName();
                    urlList.add(url);
                    SysFile sysFile = SysFile.of(fileStruct);
                    sysFile.setFileUrl(url);
                    sysFile.setUploadUserName(userName);
                    return sysFile;
                }).collect(Collectors.toList());
                boolean isSaveBatch = this.saveBatch(sysFileList);
                return isSaveBatch ? urlList : new ArrayList<>();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return urlList;
    }

    @Override
    public void downloadFile(List<String> ids, boolean iszip, HttpServletResponse response) {
        List<SysFile> sysFileList = this.list(this.lambdaQuery().getWrapper().in(SysFile::getId, ids));
        if (Objects.nonNull(sysFileList) && sysFileList.size() > 0) {
            try {
                File[] fileArr = sysFileList.stream().map(sysFile -> {
                    File f1 = new File(sysFile.getFilePath() + sysFile.getFileName());
                    if (sysFile.getFileName().equals(sysFile.getFileOriginalName())) {
                        return f1;
                    }
                    File f2 = new File(sysFile.getFilePath() + sysFile.getFileOriginalName());
                    try {
                        ActionFileUtils.copyFile(f1, f2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return f2;
                }).toArray(File[]::new);

                ActionFileUtils.build().downloadFile(response, fileArr, iszip);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean pseudoDeletionBatchByIds(List<String> ids) {
        List<SysFile> sysFiles = this.listByIds(ids);
        if (CollectionUtils.isEmpty(sysFiles)) {
            return false;
        }
        sysFiles.stream().forEach(sysFile -> sysFile.setDelFlag(DelType.lOSE.getStatus()));
        return this.updateBatchById(sysFiles);
    }

    @Override
    public boolean recoveryBaseFilesByIds(List<String> ids) {
        List<SysFile> sysFiles = this.listByIds(ids);
        if (CollectionUtils.isEmpty(sysFiles)) {
            return false;
        }
        sysFiles.stream().forEach(sysFile -> sysFile.setDelFlag(DelType.EXIST.getStatus()));
        return this.updateBatchById(sysFiles);
    }

    @Override
    public boolean removeBatchFilesByIds(List<String> ids) {
        List<SysFile> sysFiles = this.listByIds(ids);
        if (CollectionUtils.isEmpty(sysFiles)) {
            return false;
        }
        boolean isRemoveBatchByIds = this.removeBatchByIds(ids);
        if (!isRemoveBatchByIds) {
            return false;
        }
        sysFiles.stream().forEach(sysFileLose -> {
            File file = ActionFileUtils.getFile(sysFileLose.getFilePath(), sysFileLose.getFileName());
            if (Objects.nonNull(file)) {
                try {
                    ActionFileUtils.forceDeleteOnExit(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return true;
    }

    protected String getDefaultPath(FileProperties.Resource resource) {
        return resource.getLocations().split(StringPool.COMMA)[0];
    }

    protected String getDefaultPathPattern(FileProperties.Resource resource) {
        String pathPattern = resource.getPathPatterns().split(StringPool.COMMA)[0];
        pathPattern = pathPattern.replaceAll("\\**", "");
        return pathPattern;
    }

}
