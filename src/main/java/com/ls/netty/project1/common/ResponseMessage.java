package com.ls.netty.project1.common;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 22:06
 * 响应结果的封装
 */
public class ResponseMessage extends Message <OperationResult> {
    @Override
    public Class getMessageBodyDecodeClass(int opcode) {
        return OperationType.fromOpCode(opcode).getOperationResultClazz();
    }
}
