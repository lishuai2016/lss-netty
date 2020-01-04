package com.ls.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 11:35
 */
public class EchoClient {
    public static void main(String[] args) throws Exception {
        //配置client端
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();//获取pipeline对象
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));//设置日志处理器，并指定日志的级别
                            pipeline.addLast(new EchoClientHandler());//设置自己的业务处理器
                        }
                    });
            //指定服务端的IP和端口
            String host= "127.0.0.1";
            Integer port = 8080;
            //客户端连接服务端
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            //阻塞等待直到连接关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭event loop去终止全部的线程
            group.shutdownGracefully();
        }
    }
}
