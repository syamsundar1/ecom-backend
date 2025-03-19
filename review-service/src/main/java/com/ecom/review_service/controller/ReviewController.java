package com.ecom.review_service.controller;

import com.ecom.review_service.dto.ReviewRequestDTO;
import com.ecom.review_service.model.ProductReview;
import com.ecom.review_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{productId}")
    public ProductReview getProductReview(@PathVariable("productId") String productId){
        return reviewService.getProductReview(productId);
    }

    @PostMapping("/save/{productId}/{userId}")
    public ProductReview saveProductReview(@PathVariable("productId") String productId, @PathVariable("userId") Integer userId, @RequestBody ReviewRequestDTO reviewRequestDTO){
        return reviewService.saveProductReview(productId,userId,reviewRequestDTO);
    }

}
