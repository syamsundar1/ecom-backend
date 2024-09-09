package com.ecom.search_service.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponseDTO {
    private String productName;
    private String productDescription;
}
