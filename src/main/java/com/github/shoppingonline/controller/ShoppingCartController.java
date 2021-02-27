package com.github.shoppingonline.controller;

import com.github.shoppingonline.exception.NotEnoughProductInStockException;
import com.github.shoppingonline.logic.ShoppingCartService;
import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductRepository productRepository;

    public ShoppingCartController(ShoppingCartService shoppingCartService, final ProductRepository repo) {
        this.shoppingCartService = shoppingCartService;
        this.productRepository = repo;
    }

    @GetMapping("/cart")
    ResponseEntity<Map<Product, Integer>> readAllProductsInCart(Pageable page) {
        return ResponseEntity.ok(shoppingCartService.getProducts());
    }

    @PostMapping("/cart/{id}")
    void addProduct(@PathVariable int id) {
        productRepository.findById(id)
                .ifPresent(shoppingCartService::addProduct);
    }

    @DeleteMapping("/cart/{id}")
    void deleteProduct(@PathVariable int id) {
        productRepository.findById(id)
                .ifPresent(shoppingCartService::removeProduct);
    }

    @GetMapping("/cart/checkout")
    void checkOut() {
        try {
            shoppingCartService.checkOut();
        } catch (NotEnoughProductInStockException e) {
            e.printStackTrace();
        }
    }
}
