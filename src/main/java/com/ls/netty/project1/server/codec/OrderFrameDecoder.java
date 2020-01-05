package com.ls.netty.project1.server.codec;


import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 一次解码器
 * 解决半包问题
 */

public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {
//    public OrderFrameDecoder() {
//        super(Integer.MAX_VALUE,
//                0,
//                2,
//                0,
//                2);//去除头字段信息
//    }
public OrderFrameDecoder() {
    super(10240, 0, 2, 0, 2);
}
}
