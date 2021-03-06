package com.github.shoppingonline.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Page<Product> findAll(Pageable page);

    List<Product> findAll(Specification<Product> spec);

    Optional<Product> findById(Integer id);

    Product getOne(Integer id);

    Product save(Product product);

    List<Product> saveAll(Iterable<Product> products);

    void flush();

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findAllCategories();

}
