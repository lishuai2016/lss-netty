package com.ls.netty.project1.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 12:52
 * long型生成id工具类
 */
public final class IdUtil {

    private static final AtomicLong NEXT = new AtomicLong();

    private IdUtil() {//不能被实例化

    }

    public static long nextId() {//先++再返回
        return NEXT.incrementAndGet();
    }
}
