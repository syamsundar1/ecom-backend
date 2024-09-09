package com.ecom.cart_service.service;

import com.ecom.cart_service.model.Cart;
import com.ecom.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;

    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart showCart(Integer userId) {
        return cartRepository.findByUserId(userId);
    }
}
