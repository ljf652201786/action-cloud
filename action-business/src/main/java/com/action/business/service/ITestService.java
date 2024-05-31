package com.action.business.service;

import com.action.business.entity.Test;
import com.action.common.file.analysis.ExcelStruct;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITestService extends IService<Test> {

    boolean importFile(MultipartFile multipartFile, List<ExcelStruct> excelStructList);
}
