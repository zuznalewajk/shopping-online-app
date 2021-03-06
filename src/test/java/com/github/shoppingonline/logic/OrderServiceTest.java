package com.github.shoppingonline.logic;

import com.github.shoppingonline.exception.NotEnoughProductInStockException;
import com.github.shoppingonline.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Test
    @DisplayName("should throw IllegalArgumentException when cart is empty")
    void placeOrder_emptyCart_throwsIllegalArgumentException() {
        // given
        var user = new User();
        Map<Product, Integer> products = Collections.emptyMap();
        // system under test
        var toTest = new OrderService(null, null);
        // when
        var exception = catchThrowable(() -> toTest.placeOrder(products, user));
        // then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Empty cart");
    }

    @Test
    @DisplayName("should add new order and order details to repos")
    void placeOrder_productsIsOK_UserIsOk() {
        // given
        var user = new User();
        // and
        var products = new HashMap<Product, Integer>();
        products.put(new Product(), 1);
        products.put(new Product(), 2);
        // and
        var orderRepo = mock(OrderRepository.class);
        when(orderRepo.save(any(Order.class))).thenReturn(new Order(user));
        // and
        var detailsRepo = new InMemoryDetailsRepository();

        // system under test
         var toTest = new OrderService(orderRepo, detailsRepo);

        // when
        Order result = toTest.placeOrder(products, user);

        // then
        assertEquals(result.getUser().getId(), user.getId());
        assertEquals(detailsRepo.getSize(), products.size());

    }

    private static class InMemoryDetailsRepository implements OrderDetailsRepository {

        private Map<Integer, OrderDetails> detailsMap = new HashMap<>();
        private int index = 0;

        @Override
        public OrderDetails save(OrderDetails details) {
            detailsMap.put(index, details);
            index++;
            return details;
        }

        @Override
        public List<OrderDetails> findByOrder(Order order) {
            return null;
        }

        public int getSize() {
            return detailsMap.size();
        }
    }


}