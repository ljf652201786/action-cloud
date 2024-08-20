package com.action.business.controller;

import com.action.business.listener.event.ActionBusinessEvent;
import com.action.business.struct.dto.TestDto;
import com.action.business.struct.entity.Test;
import com.action.business.listener.TestImportListener;
import com.action.business.service.ITestService;
import com.action.business.struct.vo.TestImportVo;
import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.core.listener.IEventService;
import com.action.common.entity.EventStruct;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private final IEventService iEventService;
    private final SqlSessionFactory sqlSessionFactory;

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
     * @param id 对象id
     * @Description: test列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Result getInfoById(@RequestParam("id") String id) {
        return this.getInfoById(iTestService, id);
    }

    /**
     * @param testDto test对象
     * @Description: 保存test
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
//    @PreAuthorize("@ss.hasPerm('sys:test:save')")   // 开启注解权限时生效
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody TestDto testDto) {
        boolean isSave = iTestService.save(testDto);
        return Result.judge(isSave);
    }

    /**
     * @param testDto test对象
     * @Description: 更新test
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody TestDto testDto) {
        boolean isUpdate = iTestService.updateById(testDto);
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
    public Result deleteById(@RequestParam("id") String id) {
        boolean isDelete = iTestService.removeById(id);
        return Result.judge(isDelete);
    }

    /**
     * @Description: 树形选择
     * 需满足条件 1、实体对象中需要实现 ITreeNodeSelect接口
     * 2、对应实体表结构中有 ancestral(祖籍)，parentId(父节点id)字段
     * 3、实体对象中需包含有 List<T> childrenList = new ArrayList<>(); 属性
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "treeSelect", method = RequestMethod.GET)
    public Result treeSelect(Test test) {
        return this.treeSelect(iTestService, test, Test::getId);
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

    /**
     * @param multipartFile 文件对象
     * @Description: 导入excel文件
     * @return: void
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public Result ipExcel(MultipartFile multipartFile) throws IOException {
        TestImportListener importListener = new TestImportListener(iTestService);
        String msg = this.importExcel(multipartFile, TestImportVo.class, importListener);
        return Result.success(msg);
    }


    /**
     * @param response response对象
     * @param test     test对象
     * @Description: 导出excel文件
     * @return: void
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void epExcel(HttpServletResponse response, Test test) throws IOException {
        List<Test> list = iTestService.list(this.getQueryWrapper(test));
        this.exportExcel(response, Test.class, "用户列表.xlsx", "Test表", list);
    }

    /**
     * @Description: 测试推送系统事件
     * @return: void
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "testPushEvent", method = RequestMethod.GET)
    public void testPushEvent() {
        EventStruct eventStruct = new EventStruct();
        eventStruct.setMessage("事件内容");
        eventStruct.setTime(new Date());
        eventStruct.setServiceName("业务服务");
        iEventService.publishEvent(eventStruct);
        iEventService.publishEvent(ActionBusinessEvent.class, eventStruct);
    }

    /**
     * @Description: 多租户测试
     * @return: void
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "multiTenant", method = RequestMethod.GET)
    public void multiTenant() {
//        String id = "";
//        Test test = new Test();
//        test.setName("testsave");
//        iTestService.save(test);
//        id = test.getId();
//        Test tes = iTestService.getById(id);
//        test.setName("testupdate");
//        iTestService.updateById(test);
//        iTestService.removeById(id);
        Configuration configuration = sqlSessionFactory.getConfiguration();
        System.out.println(configuration.getEnvironment().getDataSource().toString());
    }
}
