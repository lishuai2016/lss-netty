package com.ls.netty.project1.server.codec;

import com.ls.netty.project1.common.ResponseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 22:27
 * server端的编码器：把响应信息写入ByteBuf中
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<ResponseMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseMessage responseMessage, List<Object> out) throws Exception {
        ByteBuf buffer = channelHandlerContext.alloc().buffer();//分配buf
        responseMessage.encode(buffer);//把响应信息写到buf中
        out.add(buffer);
    }
}
