package com.action.business.controller;

import com.action.business.entity.Test;
import com.action.business.service.ITestService;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseController;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * @Description: 测试接口控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@RequestMapping("test")
@RestController
@RequiredArgsConstructor
public class TestController implements BaseController<ITestService, Test> {
    private final ITestService iTestService;

    /**
     * @param query 查询对象
     * @Description: test列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result getDictList(Test test, BaseQuery query) {
        return this.page(iTestService, test, query);
    }

    /**
     * @param test test对象
     * @Description: 保存test
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
//    @PreAuthorize("@ss.hasPerm('sys:test:save')")   // 开启注解权限时生效
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody Test test) {
        boolean isSave = iTestService.save(test);
        return Result.judge(isSave);
    }

    /**
     * @param test test对象
     * @Description: 更新test
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody Test test) {
        boolean isUpdate = iTestService.updateById(test);
        return Result.judge(isUpdate);
    }

    /**
     * @param id test id
     * @Description: 删除test
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("id") String id) {
        boolean isDelete = iTestService.removeById(id);
        return Result.judge(isDelete);
    }

    /**
     * @param multipartFile 文件对象
     * @Description: 导入文件
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "importFile", method = RequestMethod.POST)
    public Result importFile(@RequestParam("file") MultipartFile multipartFile) {
        boolean isImport = iTestService.importFile(multipartFile, new ArrayList<>() {
        });
        return Result.judge(isImport);
    }
}
