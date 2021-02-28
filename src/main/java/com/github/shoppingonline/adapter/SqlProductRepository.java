package com.github.shoppingonline.adapter;

import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Integer> {
    @Override
    List<Product> saveAll(Iterable products);
}
