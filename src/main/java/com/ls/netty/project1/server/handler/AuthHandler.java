package com.ls.netty.project1.server.handler;

import com.ls.netty.project1.common.Operation;
import com.ls.netty.project1.common.RequestMessage;
import com.ls.netty.project1.common.auth.AuthOperation;
import com.ls.netty.project1.common.auth.AuthOperationResult;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class AuthHandler extends SimpleChannelInboundHandler<RequestMessage> {//授权的handler

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) throws Exception {//第一个请求是授权的
        try {
            Operation operation = msg.getMessageBody();
            if (operation instanceof AuthOperation) {
                AuthOperation authOperation = (AuthOperation) operation;
                AuthOperationResult authOperationResult = authOperation.execute();//执行的结果
                if (authOperationResult.isPassAuth()) {
                    log.info("pass auth");
                } else {
                    log.error("fail to auth");
                    ctx.close();
                }
            } else {
                log.error("expect first msg is auth");
                ctx.close();
            }
        } catch (Exception e) {
            log.error("exception happen for: " + e.getMessage(), e);
            ctx.close();
        } finally {
            ctx.pipeline().remove(this);//授权过的从pipeline中移除
        }

    }
}
