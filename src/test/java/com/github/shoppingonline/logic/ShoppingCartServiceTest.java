package com.github.shoppingonline.logic;

import com.github.shoppingonline.exception.NotEnoughProductInStockException;
import com.github.shoppingonline.model.Product;
import com.github.shoppingonline.model.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartServiceTest {

    @Test
    @DisplayName("should throw NotEnoughProductInStockException when there are more products in the cart than in stock.")
    void checkOut_moreProductsInCartThanInStock_throwsNotEnoughProductInStockException() {
        // given
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        var product = new Product();
        product.setName("Shirt");
        when(mockProductRepository.getOne(anyInt())).thenReturn(product);

        // system under test
        var toTest = new ShoppingCartService(mockProductRepository);
        toTest.addProduct(product);
        toTest.addProduct(product);

        // when
        var exception = catchThrowable(() -> toTest.checkOut());

        // then
        assertThat(exception)
                .isInstanceOf(NotEnoughProductInStockException.class)
                .hasMessageContaining("Not enough Shirt");

    }

    @Test
    @DisplayName("should change product quantity after checkout")
    void checkOut_adequateProductQuantityInCartAndStock_changesProductQuantity() {
        // given
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        var product = new Product();
        product.setQuantity(20);
        when(mockProductRepository.getOne(anyInt())).thenReturn(product);

        // system under test
        var toTest = new ShoppingCartService(mockProductRepository);
        toTest.addProduct(product);
        toTest.addProduct(product);

        // when
        try {
            toTest.checkOut();
        } catch (NotEnoughProductInStockException e) {
            e.printStackTrace();
        }

        // then
        assertEquals(product.getQuantity(), 18);
    }

    @Test
    @DisplayName("should return 0 when there are no products in the cart")
    void getTotal_emptyCart_returnsZero() {
        // system under test
        var toTest = new ShoppingCartService(null);

        // when
        BigDecimal result = toTest.getTotal();

        // then
        assertEquals(result, BigDecimal.ZERO);
    }






}

