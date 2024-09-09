package com.ecom.cart_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {

    private String productId;
    private Integer quantity;
    private String productName;
    private BigDecimal price;

}
