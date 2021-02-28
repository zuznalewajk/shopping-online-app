package com.github.shoppingonline.logic;

import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    Page<Product> findAll(Pageable page) {
        return productRepository.findAll(page);
    };

    List<Product> findAll(Specification<Product> spec) {
        return productRepository.findAll(spec);
    };

    Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    };

    Product getOne(Integer id) {
        return productRepository.getOne(id);
    };

    Product save(Product product) {
        return productRepository.save(product);
    };

    List<Product> saveAll(Set<Product> products) {
        return productRepository.saveAll(products);
    };

    void flush() {
        productRepository.flush();
    };
}
