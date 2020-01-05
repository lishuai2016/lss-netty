package com.ls.netty.project1.server;

import com.ls.netty.project1.server.codec.OrderFrameDecoder;
import com.ls.netty.project1.server.codec.OrderFrameEncoder;
import com.ls.netty.project1.server.codec.OrderProtocolDecoder;
import com.ls.netty.project1.server.codec.OrderProtocolEncoder;
import com.ls.netty.project1.server.handler.MetricsHandler;
import com.ls.netty.project1.server.handler.OrderServerProcessHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

import java.util.concurrent.ExecutionException;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 22:12
 */
public class Server {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("boss"));
        NioEventLoopGroup workGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));
        UnorderedThreadPoolEventExecutor businessGroup = new UnorderedThreadPoolEventExecutor(10, new DefaultThreadFactory("business"));


        //metrics
        MetricsHandler metricsHandler = new MetricsHandler();//这里需要设置为共享的handler，不然永远统计值为1

        //log   不同的位置打印不同的日志
        LoggingHandler debugLogHandler = new LoggingHandler(LogLevel.DEBUG);
        LoggingHandler infoLogHandler = new LoggingHandler(LogLevel.INFO);


        try{
            serverBootstrap.group(bossGroup,workGroup);

            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("debegLog", debugLogHandler);//给handler设置名称
                    pipeline.addLast("metricHandler", metricsHandler);

                    pipeline.addLast(new OrderFrameDecoder());
                    pipeline.addLast(new OrderFrameEncoder());

                    pipeline.addLast(new OrderProtocolEncoder());
                    pipeline.addLast(new OrderProtocolDecoder());

                    pipeline.addLast("infolog", infoLogHandler);

                    pipeline.addLast(businessGroup,new OrderServerProcessHandler());
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
