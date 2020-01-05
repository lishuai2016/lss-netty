package com.ls.netty.project1.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 服务器加上read idle check – 服务器10s 接受不到channel 的请求就断掉连接
 */
@Slf4j
public class ServerIdleCheckHandler extends IdleStateHandler {//空闲检测
    public ServerIdleCheckHandler() {
        super(10, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        if (evt == IdleStateEvent.FIRST_READER_IDLE_STATE_EVENT) {
            log.info("idle check happen, so close the connection");
            ctx.close();
            return;
        }

        super.channelIdle(ctx, evt);
    }
}
