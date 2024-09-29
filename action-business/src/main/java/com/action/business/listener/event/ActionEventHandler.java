package com.action.business.listener.event;

import com.action.call.clients.RemoteSystemClients;
import com.action.common.core.entity.LogEventStruct;
import com.action.common.core.entity.LogRequestStruct;
import com.action.common.core.listener.IActionEventHanderService;
import com.action.common.core.tool.ApplicationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * @Description: action事件处理器
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/24
 */
@Service
public class ActionEventHandler implements IActionEventHanderService {
    private static final Logger log = LoggerFactory.getLogger(ActionEventHandler.class);

    @Override
    public void invoke(Object source, BiFunction<Object, Object, Object> function) {
        if (source instanceof LogEventStruct logEventStruct) {
            //处理系统服务远程调动日志接口
            RemoteSystemClients systemClients = ApplicationUtils.getBean(RemoteSystemClients.class);
            function.apply(systemClients, logEventStruct.getData());
        }
    }

    @Override
    public BiFunction<Object, Object, Object> getFun() {
        return (o1, o2) -> {
            if (o1 instanceof RemoteSystemClients remoteSystemClients) {
                if (o2 instanceof LogRequestStruct logRequestStruct) {
                    return remoteSystemClients.save(logRequestStruct);
                }
            }
            return o1;
        };
    }
}
