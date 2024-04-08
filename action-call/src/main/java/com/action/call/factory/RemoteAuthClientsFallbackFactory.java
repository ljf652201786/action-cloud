package com.action.call.factory;

import com.action.call.clients.RemoteAuthClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/03
 */
@Component
public class RemoteAuthClientsFallbackFactory implements FallbackFactory<RemoteAuthClients> {
    private static final Logger log = LoggerFactory.getLogger(RemoteAuthClientsFallbackFactory.class);

    @Override
    public RemoteAuthClients create(Throwable cause) {
        return null;
    }
}
