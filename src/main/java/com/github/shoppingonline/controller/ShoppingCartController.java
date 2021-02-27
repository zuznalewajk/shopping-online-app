package com.github.shoppingonline.controller;

import com.github.shoppingonline.exception.NotEnoughProductInStockException;
import com.github.shoppingonline.logic.ShoppingCart;
import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ShoppingCartController {

    private final ShoppingCart shoppingCart;
    private final ProductRepository productRepository;

    public ShoppingCartController(ShoppingCart shoppingCart, final ProductRepository repo) {
        this.shoppingCart = shoppingCart;
        this.productRepository = repo;
    }

    @GetMapping("/cart")
    ResponseEntity<Map<Product, Integer>> readAllProductsInCart(Pageable page) {
        return ResponseEntity.ok(shoppingCart.getProducts());
    }

    @PostMapping("/cart/{id}")
    void addProduct(@PathVariable int id) {
        productRepository.findById(id)
                .ifPresent(shoppingCart::addProduct);
    }

    @DeleteMapping("/cart/{id}")
    void deleteProduct(@PathVariable int id) {
        productRepository.findById(id)
                .ifPresent(shoppingCart::removeProduct);
    }

    @GetMapping("/cart/checkout")
    void checkOut() {
        try {
            shoppingCart.checkOut();
        } catch (NotEnoughProductInStockException e) {
            e.printStackTrace();
        }
    }
}
