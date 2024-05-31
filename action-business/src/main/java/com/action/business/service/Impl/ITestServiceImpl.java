package com.action.business.service.Impl;

import com.action.business.entity.Test;
import com.action.business.mapper.TestMapper;
import com.action.business.service.ITestService;
import com.action.common.biz.annotation.FileExtract;
import com.action.common.file.analysis.ExcelStruct;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description: Test逻辑实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@Service
public class ITestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
    @Resource
    private TestMapper testMapper;

    @FileExtract
    @Override
    public boolean importFile(MultipartFile multipartFile, List<ExcelStruct> excelStructList) {
        System.out.println(String.format("The file name of the file:{}", multipartFile.getOriginalFilename()));
        System.out.println(String.format("The length of the content of the result set for parsing the excel file:{}", excelStructList.size()));
        return true;
    }
}
