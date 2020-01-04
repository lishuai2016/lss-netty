package com.ls.netty.project1.common.auth;

import com.ls.netty.project1.common.OperationResult;
import lombok.Data;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:14
 */
@Data
public class AuthOperationResult extends OperationResult{
    private final boolean passAuth;
}
