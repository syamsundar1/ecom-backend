package com.ecom.review_service.repository;

import com.ecom.review_service.model.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<ProductReview, String > {

    ProductReview findByProductId(String productId);

}
