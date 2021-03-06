package com.github.shoppingonline.controller;

import com.github.shoppingonline.logic.UserService;
import com.github.shoppingonline.model.OrderRepository;
import com.github.shoppingonline.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderController(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @GetMapping("/account/orders")
    ModelAndView readAllOrders() {
        ModelAndView model = new ModelAndView("/orders");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        model.addObject("orders", orderRepository.findByUser(user));

        return model;
    }


}
