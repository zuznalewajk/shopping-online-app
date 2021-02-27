package com.github.shoppingonline.exception;

import com.github.shoppingonline.model.Product;

public class NotEnoughProductInStockException extends Exception {
    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductInStockException(Product product) {
        super(String.format("Not enough %s (id = %d ) in stock. Only %d left",
                product.getName(), product.getId(), product.getQuantity()));
    }
}
