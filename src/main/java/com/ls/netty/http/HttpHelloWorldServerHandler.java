package com.ls.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 11:36
 *
 * 实现用户访问的时候返回一个欢迎： helloworld
 */
public class HttpHelloWorldServerHandler extends SimpleChannelInboundHandler<HttpObject>{

    private static final byte[] content = "helloworld".getBytes();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg){
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;

            DefaultFullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(),
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(content));
            //需要设置相应头相关数据
            response.headers()
                    .set(CONTENT_TYPE, TEXT_PLAIN)
                    .setInt(CONTENT_LENGTH, response.content().readableBytes());


            channelHandlerContext.write(response);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
