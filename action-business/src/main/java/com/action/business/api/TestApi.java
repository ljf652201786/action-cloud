package com.action.business.api;

import com.action.common.core.common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

/**
 * @Description: 测试API
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/20
 */
@HttpExchange("")
public interface TestApi {

    @PostExchange("/corerain-auth/checkCode")
    Result login(@RequestBody LoginDto loginDto);

}
