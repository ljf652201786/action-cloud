package com.action.business.network;

import com.action.common.network.properties.NetWorkManagerProperties;
import com.action.common.network.protocol.NetPipelineProtocolFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: webSocket通道
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/23
 */

public class ActionWebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    private NetWorkManagerProperties.WebSocketNet webSocketNetProperties;

    public ActionWebSocketServerInitializer(NetWorkManagerProperties.WebSocketNet webSocketNetProperties) {
        this.webSocketNetProperties = webSocketNetProperties;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        NetPipelineProtocolFactory.setDefaultCustomWebsocketPipelineProto(pipeline);
        pipeline.addLast(new ActionWebSocketServerHandler());
    }
}

