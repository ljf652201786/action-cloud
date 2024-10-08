package com.action.business.api;

import com.action.common.core.common.Result;
import com.action.common.network.service.IWebClientApi;
import org.springframework.web.service.annotation.*;

/**
 * @Description: 测试API
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/20
 */
@HttpExchange("")
public interface TestApi extends IWebClientApi {

    @GetExchange("/test/testHttp")
    Result testHttp();

}
