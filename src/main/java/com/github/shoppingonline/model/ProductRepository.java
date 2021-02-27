package com.github.shoppingonline.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepository {

    Page<Product> findAll(Pageable page);

    Optional<Product> findById(Integer id);

    Product save(Product product);

}
