package com.github.shoppingonline.logic;

import com.github.shoppingonline.model.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
