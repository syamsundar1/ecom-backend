package com.ecom.product_service.controller;

import com.ecom.product_service.dto.AddProductRequestDTO;
import com.ecom.product_service.dto.AddProductResponseDTO;
import com.ecom.product_service.model.Product;
import com.ecom.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return  productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getAllProducts(@PathVariable("productId") String productId){
        return productService.getProduct(productId);
    }

    @PostMapping("/add_product")
    @ResponseStatus(HttpStatus.CREATED)
    public AddProductResponseDTO addProduct(@RequestBody AddProductRequestDTO addProductRequestDTO){
       return productService.addProduct(addProductRequestDTO);
    }


}
