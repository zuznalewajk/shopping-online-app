package com.github.shoppingonline.model;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    List<Order> findByUser(User user);
    Order findById(int id);
}
