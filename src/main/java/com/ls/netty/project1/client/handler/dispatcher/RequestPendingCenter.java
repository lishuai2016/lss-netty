package com.ls.netty.project1.client.handler.dispatcher;

import com.ls.netty.project1.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 22:43
 *
 * 用来记录那些请求的响应返回了
 */
public class RequestPendingCenter {
    private Map<Long, OperationResultFuture> map = new ConcurrentHashMap<>();

    public void add(Long streamId, OperationResultFuture future){
        this.map.put(streamId, future);
    }

    public void set(Long streamId, OperationResult operationResult){
        OperationResultFuture operationResultFuture = this.map.get(streamId);
        if (operationResultFuture != null) {
            operationResultFuture.setSuccess(operationResult);//解除阻塞
            this.map.remove(streamId);
        }
    }
}
