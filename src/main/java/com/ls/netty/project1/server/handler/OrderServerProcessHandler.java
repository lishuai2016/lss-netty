package com.ls.netty.project1.server.handler;

import com.ls.netty.project1.common.Operation;
import com.ls.netty.project1.common.OperationResult;
import com.ls.netty.project1.common.RequestMessage;
import com.ls.netty.project1.common.ResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 22:15
 * sever端接收到的是请求信息，所以这里的泛型为RequestMessage
 *
 * SimpleChannelInboundHandler可以释放byteBUF
 */
public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage) throws Exception {
        Operation operation = requestMessage.getMessageBody();//获取请求消息中的操作
        OperationResult operationResult = operation.execute();//执行业务逻辑

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());//设置消息头部
        responseMessage.setMessageBody(operationResult);
        channelHandlerContext.writeAndFlush(responseMessage);//返回结果
    }
}
