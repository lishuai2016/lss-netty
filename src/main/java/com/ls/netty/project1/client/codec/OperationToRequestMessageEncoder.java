package com.ls.netty.project1.client.codec;

import com.ls.netty.project1.common.Operation;
import com.ls.netty.project1.common.RequestMessage;
import com.ls.netty.project1.util.IdUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 22:32
 * 把操作转化为请求信息的编码
 */
public class OperationToRequestMessageEncoder extends MessageToMessageEncoder<Operation> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Operation operation, List<Object> out) throws Exception {
        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), operation);
        out.add(requestMessage);//封装完输出
    }
}
