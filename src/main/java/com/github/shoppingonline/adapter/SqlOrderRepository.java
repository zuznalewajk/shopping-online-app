package com.github.shoppingonline.adapter;

import com.github.shoppingonline.model.Order;
import com.github.shoppingonline.model.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlOrderRepository extends OrderRepository, JpaRepository<Order, Integer> {

}
