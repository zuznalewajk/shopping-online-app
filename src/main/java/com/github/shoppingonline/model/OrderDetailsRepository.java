package com.github.shoppingonline.model;

import java.util.List;

public interface OrderDetailsRepository {
    OrderDetails save(OrderDetails details);
    List<OrderDetails> findByOrder(Order order);
}
