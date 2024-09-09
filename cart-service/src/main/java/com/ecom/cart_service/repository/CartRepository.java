package com.ecom.cart_service.repository;

import com.ecom.cart_service.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart,String> {
    Cart findByUserId(Integer userId);
}
