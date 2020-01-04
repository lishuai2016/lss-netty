package com.ls.netty.project1.common.keepalive;

import com.ls.netty.project1.common.Operation;
import com.ls.netty.project1.common.OperationResult;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:15
 * 心跳检测操作，返回的是最新的时间戳，long类型的数字
 */
public class KeepaliveOperation extends Operation {

    private long time;

    public KeepaliveOperation() {
        this.time = System.nanoTime();
    }


    @Override
    public OperationResult execute() {
        return new KeepaliveOperationResult(this.time);
    }
}
