package com.action.business.service;

import com.action.business.struct.entity.Test;
import com.action.common.file.analysis.ExcelStruct;
import com.action.common.mybatisplus.extend.base.BaseMpService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITestService extends BaseMpService<Test> {

    boolean importFile(MultipartFile multipartFile, List<ExcelStruct> excelStructList);

    List<Test> getTes(String id);
}
