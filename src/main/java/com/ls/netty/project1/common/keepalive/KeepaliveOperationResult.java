package com.ls.netty.project1.common.keepalive;

import com.ls.netty.project1.common.OperationResult;
import lombok.Data;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:15
 */
@Data
public class KeepaliveOperationResult extends OperationResult {
    private final long time;
}
