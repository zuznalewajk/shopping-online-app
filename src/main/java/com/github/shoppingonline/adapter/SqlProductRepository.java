package com.github.shoppingonline.adapter;

import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlProductRepository extends ProductRepository, JpaRepository<Product, Integer> {

}
