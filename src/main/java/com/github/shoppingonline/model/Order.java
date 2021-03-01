package com.github.shoppingonline.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private enum status {

    };
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderDetails> orderDetails;

    public int getId() {
        return id;
    }

    public User getClient() {
        return user;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
