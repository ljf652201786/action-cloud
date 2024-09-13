package com.action.business.controller;

import com.action.business.service.ITestCpService;
import com.action.business.service.Impl.SSEHelper;
import com.action.business.struct.converter.TestCpConverter;
import com.action.business.struct.entity.TestCp;
import com.action.business.struct.vo.TestCpVo;
import com.action.business.struct.vo.TestCpsVo;
import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
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
    private final SSEHelper sseHelper;

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
        iTestCpService.getOne(new QueryWrapper<TestCp>().eq("age", ""));
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

    @GetMapping("sql")
    public void testBetween21() throws SQLException {
        List<TableInfo> tableInfos = TableInfoHelper.getTableInfos();// tableClass是你实体类的全限定名
        TableInfo tableInfo = tableInfos.get(0);
        tableInfo.getFieldList(); // 获取列信息
        tableInfo.getTableName(); // 获取表名
//        System.out.println(this.getLambdaQueryWrapper().eq(TestCp::getAge, "23424").getSqlSelect());
        DatabaseMetaData metaData = this.getSSF().openSession().getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println(tableName);
        }
        ResultSet indexInfo = metaData.getIndexInfo(null, null, "biz_test", false, false);
        while (indexInfo.next()) {
            // 复合索引 同样的索引名可能会出现多次，需要考虑合并。
            System.out.println("索引：" + indexInfo.getString("INDEX_NAME"));
            System.out.println("COLUMN_NAME" + indexInfo.getString("COLUMN_NAME"));
            System.out.println("TYPE" + indexInfo);
            System.out.println("INDEX_QUALIFIER" + indexInfo);
            System.out.println("NON_UNIQUE" + indexInfo);
            System.out.println("CARDINALITY" + indexInfo);
        }
        System.out.println(tables);
    }


    @GetMapping("dd")
    public Result dd() {
        Flux<ServerSentEvent> sseStream = WebClientUtils.defaultWebClient("", iWebClientAuthService).post()
                // 请求头信息
                .bodyValue("你的请求头体")
                .retrieve()
                .bodyToFlux(ServerSentEvent.class);
// 接收event-stream数据(***这里异步的***)
        Flux.from(sseStream)
                .doOnNext(sseEvent -> {
                    // 接收到数据(数据是什么类型取决于SSE服务端，我这里是LinkedHashMap)
                    String data = (String) sseEvent.data();
                    // TODO ...对数据处理
                    System.out.println(data);
                })
                .doOnError(error -> {
                    System.out.println("连接断开: {}" + error.getMessage());
                })
                .doOnComplete(() -> {
                    System.out.println("完成");
                }).subscribe();
        return null;
    }

    @PostMapping(value = "dds")
    public SseEmitter codeByTsl(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 获得请求体(这里requestBody占时没用，如果需要使用就用这种方式获取)
        /*WebClientBody webClientBody = new WebClientBody();
        webClientBody.setServiceUrl("777676767");
        WebClient webClient = WebClientUtils.defaultWebClient("http://127.0.0.1:6666/testcp/sse");
        WebClientUtils.post(webClient, null, null, webClientBody, Flux.class);*/

        WebClientBody webClientBody = new WebClientBody();
        webClientBody.setServiceUrl("567890");
        // SSE服务器端地址
        WebClient webClient = WebClient.create("http://127.0.0.1:6666/testcp/eee");
        Flux<String> sseStream = webClient.post()
                // 请求头信息
//                .header(HttpHeaders.AUTHORIZATION, "SSE服务器TOKEN")
                .bodyValue(webClientBody)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(String.class));

        Flux.from(sseStream)
                .doOnNext(sseEvent -> {
                    // 具体什么类型需要看你SSE服务返回什么
//                    LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) sseEvent.data();
//
//                    // 推送消息给客户端
//                    sendSSE(sseEmitter, data);
                    System.out.println("343453453435433454--" + sseEvent);
                })
                .doOnError(error -> {
                    System.out.println("234");
                })
                .doOnComplete(() -> {
                    //完成后执行
                    System.out.println("2322224");

                }).subscribe(sink -> {
                    System.out.println("hell" + sink.toString());
                })/*.delayElements(Duration.ofMillis(1000))*/;
        // 返回是SseEmitter对象
//        return sseHelper.receiveSSE();
        return null;
    }

    @PostMapping(value = "sse", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void dsd(HttpServletResponse response, HttpServletRequest request) throws IOException {
    public Result dsd(@RequestBody WebClientBody webClientBody) throws IOException {
//        String requestBody = getRequestBody(request);
//        System.out.println(requestBody);
        System.out.println(webClientBody.getServiceUrl());
        return Result.success("hhkhkhkhkh");
    }

    /**
     * 获得请求体信息
     */
    private static String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return requestBody.toString();
    }


    // 维护SseEmitter对象
    Map<String, SseEmitter> map = new HashMap<String, SseEmitter>();

    @PostMapping(value = "eee", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sendSSE() {
        // 超时时间300s
        SseEmitter sseEmitter = new SseEmitter(300_000L);
        // 必须使用新线程去做推送的功能，需要先返回SseEmitter对象给前端
        new Thread(() -> {
            try {
                sseEmitter.send(SseEmitter.event()
                        .reconnectTime(5000)    // 与前端断开连接后5s后尝试重连
                        .id(UUID.randomUUID().toString())
                        .name("name")
                        .data("你的数据"));

            } catch (IOException e) {
                System.out.println("SEE推送消息失败, 失败消息: {}");
                sseEmitter.completeWithError(e);
            }
        }).start();


        // 先返回SseEmitter对象给前端
        return sseEmitter;
    }
}
