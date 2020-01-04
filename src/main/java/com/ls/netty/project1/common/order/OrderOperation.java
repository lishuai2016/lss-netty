package com.ls.netty.project1.common.order;

import com.ls.netty.project1.common.Operation;
import com.ls.netty.project1.common.OperationResult;
import lombok.Data;

/**
 * @program: lss-netty
 * @author: lishuai
 * @create: 2020-01-04 13:15
 * 业务逻辑处理，生成处理是否成功标记
 */
@Data
public class OrderOperation extends Operation {
    private  int tableId;
    private  String dish;

    public OrderOperation(int tableId,String dish) {
        this.tableId = tableId;
        this.dish = dish;
    }

    @Override
    public OperationResult execute() {
        System.out.println("order's executing startup with orderRequest: " + toString());
        //execute order logic
        System.out.println("order's executing complete");
        OrderOperationResult orderResponse = new OrderOperationResult(tableId, dish, true);
        return orderResponse;
    }
}
