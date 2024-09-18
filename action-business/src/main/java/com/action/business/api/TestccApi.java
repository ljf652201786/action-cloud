package com.action.business.api;

import com.action.common.core.common.Result;
import com.action.common.network.service.IWebClientApi;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @Description: 测试API
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/20
 */
@HttpExchange("")
public interface TestccApi extends IWebClientApi {

    @GetExchange("/test/testHttp_2")
    Result testHttp_2();

}
