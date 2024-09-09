package com.ecom.search_service.controller;

import com.ecom.search_service.dto.ProductListResponseDTO;
import com.ecom.search_service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public List<ProductListResponseDTO> getProductList(){
        return productService.getProductList();
    }

}
