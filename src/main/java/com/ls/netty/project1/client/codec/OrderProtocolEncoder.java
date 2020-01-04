package com.ls.netty.project1.client.codec;

import com.ls.netty.project1.common.RequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;


import java.util.List;

/**
 * 把请求信息封装成buffer传递出去
 */

public class OrderProtocolEncoder extends MessageToMessageEncoder<RequestMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestMessage requestMessage, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();//分配buffer
        requestMessage.encode(buffer);

        out.add(buffer);
    }
}
