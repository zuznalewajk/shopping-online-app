package com.github.shoppingonline.controller;

import com.github.shoppingonline.exception.NotEnoughProductInStockException;
import com.github.shoppingonline.logic.ShoppingCartService;
import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    ModelAndView shoppingCart() {

        ModelAndView model = new ModelAndView("/shoppingCart");
        model.addObject("products", shoppingCartService.getProducts());
        //model.addObject("total", shoppingCartService.getTotal());
        //return ResponseEntity.ok(productRepository.findAll(page).getContent());
        return model;
    }

    @GetMapping("/cart/{id}")
    ModelAndView addProduct(@PathVariable int id) {
        productRepository.findById(id)
                .ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/cart/delete/{id}")
    ModelAndView deleteProduct(@PathVariable int id) {
        productRepository.findById(id)
                .ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/cart/checkout")
    ModelAndView checkOut() {
        try {
            shoppingCartService.checkOut();
        } catch (NotEnoughProductInStockException e) {
            e.printStackTrace();
        }
        return shoppingCart();
    }
}
