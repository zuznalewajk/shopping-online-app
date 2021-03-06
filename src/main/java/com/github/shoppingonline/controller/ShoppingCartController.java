package com.github.shoppingonline.controller;

import com.github.shoppingonline.exception.NotEnoughProductInStockException;
import com.github.shoppingonline.logic.OrderService;
import com.github.shoppingonline.logic.ShoppingCartService;
import com.github.shoppingonline.logic.UserService;
import com.github.shoppingonline.model.Order;
import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import com.github.shoppingonline.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, final ProductRepository repo, OrderService orderService, final UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.productRepository = repo;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/cart")
    ModelAndView shoppingCart() {
        ModelAndView model = new ModelAndView("/shoppingCart");
        model.addObject("products", shoppingCartService.getProducts());
        model.addObject("total", shoppingCartService.getTotal());
        model.addObject("totalQuantityOfProducts", shoppingCartService.getQuantityOfProductsInCart());
        model.addObject("isEmpty", shoppingCartService.isEmpty());

        return model;
    }

    @PostMapping("/cart/add")
    String addProduct(@RequestParam("id") Product product) {
        shoppingCartService.addProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/cart/delete")
    ModelAndView deleteProduct(@RequestParam("id") Product product) {
        shoppingCartService.removeProduct(product);
        return shoppingCart();
    }

    @GetMapping("/cart/clear")
    ModelAndView clear() {
        shoppingCartService.clear();
        return shoppingCart();
    }

    @Transactional
    @GetMapping("/cart/checkout")
    ModelAndView checkOut() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByEmail(auth.getName());
            Order order = orderService.placeOrder(shoppingCartService.getProducts(), user);
            shoppingCartService.checkOut();
            ModelAndView model = new ModelAndView("/orderSummary");
            model.addObject("order", order);
            return model;

        } catch (NotEnoughProductInStockException e) {
            shoppingCartService.clear();
            e.printStackTrace();
        }

        return shoppingCart();
    }

}
