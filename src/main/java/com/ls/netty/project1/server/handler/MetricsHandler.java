package com.ls.netty.project1.server.handler;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 度量统计
 */

@ChannelHandler.Sharable//可共享标记
public class MetricsHandler extends ChannelDuplexHandler {

    private AtomicLong totalConnectionNumber = new AtomicLong();

    {

        MetricRegistry metricRegistry = new MetricRegistry();

        metricRegistry.register("totalConnectionNumber", new Gauge<Long>() {
            @Override
            public Long getValue() {
                return totalConnectionNumber.longValue();
            }
        });
        //展示数据，按照一定的周期打印
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(10, TimeUnit.SECONDS);

        JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {//创建链接
        totalConnectionNumber.incrementAndGet();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {//链接的释放
        totalConnectionNumber.decrementAndGet();
        super.channelInactive(ctx);
    }
}
