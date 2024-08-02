package com.action.business.network;

import com.action.common.network.protocol.ActionNetWorkProtocolFormat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description: websocket协议处理类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/23
 */

public class ActionWebSocketServerHandler extends SimpleChannelInboundHandler<ActionNetWorkProtocolFormat.Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ActionNetWorkProtocolFormat.Message message) throws Exception {
        System.out.println(String.format("time：%s, The obtained data is：%s", message.getTime(), message.getWebSockerMsg().getMsgContent()));

        ActionNetWorkProtocolFormat.WebSockerMsg niaho = ActionNetWorkProtocolFormat.WebSockerMsg.newBuilder().setMsgContent("niaho").build();
        channelHandlerContext.writeAndFlush(niaho);
    }
}
