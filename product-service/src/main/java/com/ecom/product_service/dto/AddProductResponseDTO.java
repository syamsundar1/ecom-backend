package com.ecom.product_service.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AddProductResponseDTO(String title,
                                    BigDecimal price,
                                    String description,
                                    String category,
                                    String image,
                                    BigDecimal rating) {
}
