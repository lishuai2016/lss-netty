package com.ls.netty.project1.common;

import com.ls.netty.project1.common.auth.AuthOperation;
import com.ls.netty.project1.common.auth.AuthOperationResult;
import com.ls.netty.project1.common.keepalive.KeepaliveOperation;
import com.ls.netty.project1.common.keepalive.KeepaliveOperationResult;
import com.ls.netty.project1.common.order.OrderOperation;
import com.ls.netty.project1.common.order.OrderOperationResult;

import java.util.function.Predicate;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:26
 * 操作类型枚举：编码，操作类型，操作结果类型
 */
public enum OperationType {
    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    KEEPALIVE(2, KeepaliveOperation.class, KeepaliveOperationResult.class),
    ORDER(3, OrderOperation.class, OrderOperationResult.class);

    private int opCode;
    private Class<? extends Operation> operationClazz;
    private Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz, Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }

    public int getOpCode() {
        return opCode;
    }

    public Class<? extends Operation> getOperationClazz() {
        return operationClazz;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }

    public static OperationType fromOpCode(int type){
        return getOperationType(requestType -> requestType.opCode == type);
    }

    public static OperationType fromOperation(Operation operation){
        return getOperationType(requestType -> requestType.operationClazz == operation.getClass());
    }

    private static OperationType getOperationType(Predicate<OperationType> predicate){
        OperationType[] values = values();
        for (OperationType operationType : values) {
            if(predicate.test(operationType)){
                return operationType;
            }
        }

        throw new AssertionError("no found type");
    }
}
