package com.action.business.controller;

import com.action.business.service.ITestCpService;
import com.action.business.struct.converter.TestCpConverter;
import com.action.business.struct.entity.TestCp;
import com.action.business.struct.vo.TestCpVo;
import com.action.business.struct.vo.TestCpsVo;
import com.action.business.struct.vo.TestVo;
import com.action.common.biz.base.BaseController;
import com.action.common.core.base.BaseSecurityMenu;
import com.action.common.core.common.Result;
import com.action.common.core.service.RedisCacheServices;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.common.network.service.IWebClientAuthService;
import com.action.common.network.service.IWebClientService;
import com.action.common.network.struct.WebClientBody;
import com.action.common.network.utils.WebClientUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

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
    private final IWebClientAuthService iWebClientAuthService;
    private final RedisCacheServices redisCacheServices;

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

}
