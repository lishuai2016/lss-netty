package com.ls.netty.project1.common.order;

import com.ls.netty.project1.common.OperationResult;
import lombok.Data;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:15
 */
@Data
public class OrderOperationResult extends OperationResult {
    private final int tableId;
    private final String dish;
    private final boolean complete;
}
