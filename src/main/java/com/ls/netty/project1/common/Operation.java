package com.ls.netty.project1.common;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:11
 * 一次动作的抽象为一个操作
 */
public abstract class Operation extends MessageBody{

    public abstract OperationResult execute();//一次操作的执行返回一个执行结果也是一个MessageBody
}
