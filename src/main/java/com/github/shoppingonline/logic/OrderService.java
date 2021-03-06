package com.github.shoppingonline.logic;

import com.github.shoppingonline.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository detailsRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailsRepository detailsRepository) {
        this.orderRepository = orderRepository;
        this.detailsRepository = detailsRepository;
    }

    public Order placeOrder(Map<Product, Integer> products, User user) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Empty cart");
        }
        var newOrder =  new Order(user);
        Order order = orderRepository.save(newOrder);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            var orderDetails = new OrderDetails(entry.getKey(), order, entry.getValue());
            detailsRepository.save(orderDetails);
        }
        return order;
    }

    public List<Order> findOrderForUser(User user) {
        return orderRepository.findByUser(user);
    }
}
