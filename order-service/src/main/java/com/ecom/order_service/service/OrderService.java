package com.ecom.order_service.service;

import com.ecom.order_service.model.Order;
import com.ecom.order_service.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public Order showOrder(Integer userId) {

        return orderRepository.findByUserId(userId);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
