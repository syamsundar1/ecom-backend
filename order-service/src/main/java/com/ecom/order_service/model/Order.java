package com.ecom.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String orderId;
    private Integer userId;
    private Integer addressId;
    private String orderStatus;
    private List<OrderItems> orderItems;
}
