package com.ecom.order_service.controller;

import com.ecom.order_service.model.Order;
import com.ecom.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/show/{userId}")
    public Order showOrder(@PathVariable("userId") Integer userId){
        return orderService.showOrder(userId);
    }

    @PostMapping("/save")
    public Order saveOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }


}
