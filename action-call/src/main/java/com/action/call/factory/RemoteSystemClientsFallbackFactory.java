package com.action.call.factory;

import com.action.call.clients.RemoteSystemClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 远程调用失败处理类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/03
 */
@Component
public class RemoteSystemClientsFallbackFactory implements FallbackFactory<RemoteSystemClients> {
    private static final Logger log = LoggerFactory.getLogger(RemoteSystemClientsFallbackFactory.class);

    @Override
    public RemoteSystemClients create(Throwable cause) {
        return null;
    }
}
