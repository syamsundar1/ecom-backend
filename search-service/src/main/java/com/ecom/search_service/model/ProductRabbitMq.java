package com.ecom.search_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRabbitMq {
    private String productId;
    private String title;
    private String description;
}
