package com.action.business.controller;

import com.action.business.service.ITestCpService;
import com.action.business.struct.converter.TestCpConverter;
import com.action.business.struct.entity.TestCp;
import com.action.business.struct.vo.TestCpVo;
import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
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
    public Result getDictList(TestCp test, BaseQuery query) {
        return this.page(iTestCpService, test, query);
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
        this.setConverter(TestCpVo.class);
        TestCpVo vo = toVo(iTestCpService.getById("1"));
        System.out.println(vo);
        TestCpVo infoById = iTestCpService.getInfoById("1", TestCpVo.class);
        List<TestCp> tesCp = iTestCpService.getTesCp("1");
        return this.getInfoById(iTestCpService, "1", TestCpVo.class);

    }

}
