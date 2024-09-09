package com.ecom.product_service.dto;

import java.math.BigDecimal;

public record AddProductRequestDTO(
         String title,
         BigDecimal price,
         String description,
         String category,
         String image,
         BigDecimal rating
) {
}
