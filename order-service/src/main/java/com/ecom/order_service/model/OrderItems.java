package com.ecom.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {

    private String productId;
    private Integer quantity;
    private BigDecimal price;

}
