package com.ecom.cart_service.controller;

import com.ecom.cart_service.model.Cart;
import com.ecom.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String hello(){
        return "Hello";
    }

    @GetMapping("/show/{userId}")
    public Cart showCart(@PathVariable("userId") Integer userId){
        return cartService.showCart(userId);
    }

    @PostMapping("/save")
    public Cart saveCart(@RequestBody Cart cart){
       return cartService.saveCart(cart);
    }

}
