package com.ecom.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String productId;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;
    private BigDecimal rating;

    public Product(String title,String description){
        this.title = title;
        this.description = description;
    }

}
