package com.ecom.search_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    private String productId;
    private String productName;
    private String productDescription;

    public Product(String productName, String productDescription){
        this.productName = productName;
        this.productDescription = productDescription;
    }
}
