package com.github.shoppingonline.model;

import javax.persistence.IdClass;
import java.io.Serializable;

@IdClass(OrderDetails.class)
public class OrderDetailsKey implements Serializable {

        private int product;
        private int order;

   public OrderDetailsKey() {
        }

    public OrderDetailsKey(int product, int order) {
        this.product = product;
        this.order = order;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
