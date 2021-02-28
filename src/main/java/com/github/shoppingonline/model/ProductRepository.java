package com.github.shoppingonline.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository {

    Page<Product> findAll(Pageable page);

    List<Product> findAll(Specification<Product> spec);

    Optional<Product> findById(Integer id);

    Product getOne(Integer id);

    Product save(Product product);

    List<Product> saveAll(Iterable<Product> products);

    void flush();

}
