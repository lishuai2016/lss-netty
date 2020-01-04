package com.ls.netty.project1.common.auth;

import com.ls.netty.project1.common.Operation;
import com.ls.netty.project1.common.OperationResult;
import lombok.Data;
import lombok.extern.java.Log;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:14
 * 授权操作，返回一个授权结果，true或者false
 */
@Data
@Log
public class AuthOperation extends Operation {
    private final String username;
    private final String password;

    @Override
    public OperationResult execute() {
        if ("admin".equalsIgnoreCase(this.username)) {//这里模拟简单的授权
            return new AuthOperationResult(true);
        } else {
            return new AuthOperationResult(false);
        }
    }
}
