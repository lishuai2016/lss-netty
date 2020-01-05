package com.ls.netty.project1.client;

import com.ls.netty.project1.client.codec.OrderFrameDecoder;
import com.ls.netty.project1.client.codec.OrderFrameEncoder;
import com.ls.netty.project1.client.codec.OrderProtocolDecoder;
import com.ls.netty.project1.client.codec.OrderProtocolEncoder;
import com.ls.netty.project1.common.RequestMessage;
import com.ls.netty.project1.common.order.OrderOperation;
import com.ls.netty.project1.util.IdUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutionException;

/**
 * 发送信息。没有获取结果
 */

public class ClientV0 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);

        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            bootstrap.group(group);

            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {//注意编解码的顺序
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new OrderFrameDecoder());
                    pipeline.addLast(new OrderFrameEncoder());

                    pipeline.addLast(new OrderProtocolEncoder());
                    pipeline.addLast(new OrderProtocolDecoder());

                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888);

            channelFuture.sync();//保证先建立了链接

            RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), new OrderOperation(1001, "tudou"));
//模拟发送多次请求
//            for (int i = 0; i < 20;i++) {
//                channelFuture.channel().writeAndFlush(requestMessage);//发送信息。没有获取结果
//            }

            channelFuture.channel().writeAndFlush(requestMessage);//发送信息。没有获取结果

            channelFuture.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
    }

}
