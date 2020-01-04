package com.ls.netty.project1.common;

import com.ls.netty.project1.util.JsonUtil;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.charset.Charset;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:04
 * 消息的抽象，通过泛型，内部包含一个继承MessageBody的属性
 * 包含两个属性：
 * 1、消息头；
 * 2、消息体；
 *
 * 此外，这个消息的抽象类内部还包含了编解码方法
 */
@Data
public abstract class Message<T extends MessageBody> {
    private MessageHeader messageHeader;//消息头
    private T messageBody;//消息体

    public T getMessageBody(){
        return messageBody;
    }

    //需要对消息体的内容进行编解码，解码的时候需要知道把消息的内容解码对象的类型


    public abstract Class<T> getMessageBodyDecodeClass(int opcode);//返回值为class

    //1、编码。把消息写入bytebuf

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes());
    }
    //2、解码。从byteBuf解析出消息的内容到messageHeader和messageBody中

    public void decode(ByteBuf msg) {
        int version = msg.readInt();
        int opCode = msg.readInt();
        long streamId = msg.readLong();
        this.messageHeader = new MessageHeader(version,opCode,streamId);
        //根据操作类型获取对应的解码实例
        Class<T> bodyClazz = getMessageBodyDecodeClass(opCode);
        T body = JsonUtil.fromJson(msg.toString(Charset.forName("UTF-8")), bodyClazz);
        this.messageBody = body;
    }
}
