package com.ls.netty.project1.client.handler;

import io.netty.handler.timeout.IdleStateHandler;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-05 02:04
 *
 * 客户端5s 不发送数据就发一个keepalive
 */
public class ClientIdleCheckHandler extends IdleStateHandler {

    public ClientIdleCheckHandler() {
        super(0, 5, 0);
    }

}
