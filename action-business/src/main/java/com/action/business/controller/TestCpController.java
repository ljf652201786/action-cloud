package com.action.business.controller;

import com.action.business.service.ITestCpService;
import com.action.business.struct.converter.TestCpConverter;
import com.action.business.struct.entity.TestCp;
import com.action.business.struct.vo.TestCpVo;
import com.action.business.struct.vo.TestCpsVo;
import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 测试接口控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@RequestMapping("testcp")
@RestController
@RequiredArgsConstructor
public class TestCpController implements BaseController<ITestCpService, TestCp> {
    private final ITestCpService iTestCpService;

    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(TestCp test, BaseQuery query) {
        this.setConverter(TestCpVo.class);
        IPage<TestCpVo> page = (IPage) iTestCpService.pageList(test, query);
        List<TestCpVo> listVo = this.toListVo(page.getRecords(), TestCpVo.class);
        listVo.stream().forEach(System.out::println);
        return this.page(iTestCpService, test, query, TestCpVo.class);
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
        return this.getInfoById(iTestCpService, id, TestCpConverter.INSTANCE::testCpToVo);
    }

    @RequestMapping(value = "getTestsub", method = RequestMethod.GET)
    public Result getTestsub() {
        iTestCpService.getOne(new QueryWrapper<TestCp>().eq("age",""));
        this.setConverter(TestCpVo.class);
        TestCpVo vo = toVo(iTestCpService.getById("1"));
        System.out.println(vo);
        TestCpVo infoById = iTestCpService.getInfoById("1", TestCpVo.class);
        List<TestCp> tesCp = iTestCpService.getTesCp("1");
        Result result = this.selectListBy(iTestCpService, (e) -> {
            return e.getById("1");
        }, TestCpVo.class);
        Result re = this.getInfoById(iTestCpService, "1", TestCpVo.class);
        TestCp testCp = new TestCp();
//        testCp.setId("1");
//        testCp.setAge("989898");
//        testCp.setRemark("林靖峰");
        List<TestCpsVo> list = iTestCpService.getSelectList(testCp, TestCpsVo.class);
        list.stream().forEach(System.out::println);
        return this.getList(iTestCpService, new TestCp(), TestCpVo.class);
    }

    @GetMapping("code")
    public void testBetween1(@RequestParam("code") String code) {
        System.out.println("接收" + code);
    }

}
