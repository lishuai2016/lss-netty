package com.ls.netty.project1.server.codec;

import com.ls.netty.project1.common.RequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 22:24
 * 二次解码
 * server端的解码器：从byteBuf从解析出信息封装在RequestMessage中
 */
public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.decode(byteBuf);
        out.add(requestMessage);
    }
}
