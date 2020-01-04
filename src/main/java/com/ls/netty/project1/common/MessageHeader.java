package com.ls.netty.project1.common;

import lombok.Data;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:05
 */
@Data
public class MessageHeader {
    private int version=1;//版本
    private int opCode;//信息操作类型
    private long streamId;//消息编号
    public MessageHeader(){}

    public MessageHeader(int version,int opCode,long streamId) {
        this.version = version;
        this.opCode = opCode;
        this.streamId = streamId;
    }
}
