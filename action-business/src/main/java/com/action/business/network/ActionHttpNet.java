package com.action.business.network;

import com.action.common.network.properties.NetWorkManagerProperties;
import com.action.common.network.support.ActionTcpNet;

/**
 * @Description: Http网络
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/26
 */

public class ActionHttpNet extends ActionTcpNet {

    private NetWorkManagerProperties.HttpNet httpNetProperties;

    public ActionHttpNet(NetWorkManagerProperties.HttpNet httpNetProperties) {
        super(httpNetProperties);
        this.httpNetProperties = httpNetProperties;
    }

    @Override
    public TransmissionProtocol getTransmissionProtocol() {
        return TRANSMISSION_PROTOCOL.Http;
    }

}
