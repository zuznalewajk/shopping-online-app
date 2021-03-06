package com.github.shoppingonline.logic;

import com.github.shoppingonline.exception.NotEnoughProductInStockException;
import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class ShoppingCartService {

    private Map<Product, Integer> products = new HashMap<>();
    private final ProductRepository productRepository;

    public ShoppingCartService(final ProductRepository repository) {
        this.productRepository = repository;
    }

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1) {
                products.replace(product, products.get(product) - 1);
            } else {
                products.remove(product);
            }
        } else throw new IllegalArgumentException("Product not found");
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(product -> product.getKey().getPrice()
                        .multiply(BigDecimal.valueOf(product.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public void clear() {
        products.clear();
    }

    public void checkOut() throws NotEnoughProductInStockException {

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {

            Product product = productRepository.getOne(entry.getKey().getId());

            if (product == null)
                throw new NoSuchElementException();

            if (entry.getValue() > product.getQuantity())
                throw new NotEnoughProductInStockException(product);

            entry.getKey().setQuantity( product.getQuantity() - entry.getValue() );

        }

        productRepository.saveAll(products.keySet());
        productRepository.flush();
        this.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int getQuantityOfProductsInCart() {
        return products.values().stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

}
