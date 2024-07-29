package com.action.business.network;

import com.action.common.network.properties.NetWorkManagerProperties;
import com.action.common.network.support.ActionTcpNet;

/**
 * @Description: 自定义websocket网络
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/26
 */

public class ActionWebSockerNet extends ActionTcpNet {

    private NetWorkManagerProperties.WebSocketNet webSocketNet;

    public ActionWebSockerNet(NetWorkManagerProperties.WebSocketNet webSocketNet) {
        super(webSocketNet);
        this.webSocketNet = webSocketNet;
    }

    @Override
    public TransmissionProtocol getTransmissionProtocol() {
        return TRANSMISSION_PROTOCOL.WEB_SOCKET;
    }

}
