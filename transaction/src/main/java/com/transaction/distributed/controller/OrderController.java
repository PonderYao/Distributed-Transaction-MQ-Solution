package com.transaction.distributed.controller;

import com.transaction.distributed.base.BaseApiService;
import com.transaction.distributed.base.ResponseBase;
import com.transaction.distributed.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 21:51
 */
@RestController
public class OrderController extends BaseApiService {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrder")
    public ResponseBase addOrder() {
        return orderService.addOrderAndDispatch();
    }

}