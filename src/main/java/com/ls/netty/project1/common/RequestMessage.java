package com.ls.netty.project1.common;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 21:47
 * 请求信息封装
 */
public class RequestMessage extends Message<Operation> {
    @Override
    public Class getMessageBodyDecodeClass(int opcode) {
        return OperationType.fromOpCode(opcode).getOperationClazz();
    }

    public RequestMessage(){}

    public RequestMessage(Long streamId, Operation operation){
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setStreamId(streamId);
        messageHeader.setOpCode(OperationType.fromOperation(operation).getOpCode());
        this.setMessageHeader(messageHeader);//设置请求信息头部
        this.setMessageBody(operation);//具体的请求信息
    }
}
