package com.ecom.product_service.service;

import com.ecom.product_service.ProductServiceApplication;
import com.ecom.product_service.config.RabbitMqConfig;
import com.ecom.product_service.constants.MessageConstants;
import com.ecom.product_service.dto.AddProductRequestDTO;
import com.ecom.product_service.dto.AddProductResponseDTO;
import com.ecom.product_service.model.Product;
import com.ecom.product_service.model.ProductRabbitMq;
import com.ecom.product_service.repository.ProductRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public AddProductResponseDTO addProduct(AddProductRequestDTO dto) {
        Product product = new Product();
        product.setTitle(dto.title());
        product.setCategory(dto.category());
        product.setPrice(dto.price());
        product.setRating(dto.rating());
        product.setDescription(dto.description());
        product.setImage(dto.image());
        productRepository.save(product);

        ProductRabbitMq rabbitMqProduct = new ProductRabbitMq(product.getProductId(), product.getTitle(), product.getDescription());
        rabbitTemplate.convertAndSend(MessageConstants.EXCHANGE,MessageConstants.ROUTING_KEY,rabbitMqProduct);
        System.out.println("Product is sent from product-service product ID " + product.getProductId());


        return AddProductResponseDTO
                .builder()
                .title(product.getTitle())
                .category(product.getCategory())
                .price(product.getPrice())
                .rating(product.getRating())
                .description(product.getDescription())
                .image(product.getImage())
                .build();
    }
}
