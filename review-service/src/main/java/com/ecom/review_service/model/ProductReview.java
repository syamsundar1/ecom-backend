package com.ecom.review_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReview {

    @Id
    private String reviewId;
    private String productId;
    private Integer userId;
    private String description;
    private BigDecimal rating;


}
