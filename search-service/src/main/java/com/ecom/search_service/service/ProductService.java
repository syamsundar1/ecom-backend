package com.ecom.search_service.service;

import com.ecom.search_service.constants.MessageConstants;
import com.ecom.search_service.dto.ProductListResponseDTO;
import com.ecom.search_service.model.Product;
import com.ecom.search_service.model.ProductRabbitMq;
import com.ecom.search_service.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @RabbitListener(queues = MessageConstants.QUEUE)
    public void consumerRabbitMqProduct(ProductRabbitMq productRabbitMq){
        Product product = new Product(productRabbitMq.getProductId(), productRabbitMq.getTitle(), productRabbitMq.getDescription());
        productRepository.save(product);
        System.out.println("New product is added from product-service to search service "+ productRabbitMq.getTitle() + " Product ID " + productRabbitMq.getProductId());
    }


    public List<ProductListResponseDTO> getProductList() {
        Iterable<Product> products = productRepository.findAll();
        List<ProductListResponseDTO> productListResponseDTOList = new ArrayList<>();
        products.forEach(product -> {
                    productListResponseDTOList
                            .add(new ProductListResponseDTO(
                                    product.getProductName(),
                                    product.getProductDescription()));
        });
        return productListResponseDTOList;
    }
}
