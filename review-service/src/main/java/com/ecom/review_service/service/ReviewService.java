package com.ecom.review_service.service;

import com.ecom.review_service.dto.ReviewRequestDTO;
import com.ecom.review_service.model.ProductReview;
import com.ecom.review_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ProductReview getProductReview(String productId) {
        return reviewRepository.findByProductId(productId);
    }

    public ProductReview saveProductReview(String productId, Integer userId, ReviewRequestDTO dto) {
        ProductReview productReview = new ProductReview();
        productReview.setProductId(productId);
        productReview.setUserId(userId);
        productReview.setRating(dto.getRating());
        productReview.setDescription(dto.getDescription());
        return reviewRepository.save(productReview);
    }
}
