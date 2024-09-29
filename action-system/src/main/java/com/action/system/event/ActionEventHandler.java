package com.action.system.event;

import com.action.common.core.entity.LogEventStruct;
import com.action.common.core.entity.LogRequestStruct;
import com.action.common.core.listener.IActionEventHanderService;
import com.action.common.core.tool.ApplicationUtils;
import com.action.system.manager.service.ISysLogRequestService;
import com.action.system.manager.struct.converter.LogRequestConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @Description: action事件处理器
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/24
 */
@Service
public class ActionEventHandler implements IActionEventHanderService {
    private static final Logger log = LoggerFactory.getLogger(ActionEventHandler.class);
    private static List<String> ignoreUrls = List.of("/logLogin/listPage", "/logLogin/save", "/logRequest/listPage", "/logRequest/save", "/logSms/listPage", "/logSms/save");

    @Override
    public void invoke(Object source, BiFunction<Object, Object, Object> function) {
        if (source instanceof LogEventStruct logEventStruct) {
            //处理系统服务远程调动日志接口
            ISysLogRequestService iSysLogRequestService = ApplicationUtils.getBean(ISysLogRequestService.class);
            function.apply(iSysLogRequestService, logEventStruct.getData());
        }
    }

    @Override
    public BiFunction<Object, Object, Object> getFun() {
        return (o1, o2) -> {
            if (o1 instanceof ISysLogRequestService iSysLogRequestService) {
                if (o2 instanceof LogRequestStruct logRequestStruct) {
                    return ignoreUrls.contains(logRequestStruct.getMethodName()) ? o2 :
                            iSysLogRequestService.save(LogRequestConverter.INSTANCE.logRequestStructToSysLogRequest(logRequestStruct));
                }
            }
            return o2;
        };
    }
}
