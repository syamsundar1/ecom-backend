package com.ecom.product_service.repository;

import com.ecom.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends MongoRepository<Product,String> {
}
