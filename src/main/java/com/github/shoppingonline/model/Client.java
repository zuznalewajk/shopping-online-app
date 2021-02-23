package com.github.shoppingonline.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Client first name must be not empty.")
    private String firstName;

    @NotBlank(message = "Client last name must be not empty.")
    private String lastName;

    @NotBlank(message = "Client email must be not empty.")
    private String email;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "client")
    private Set<Order> orders;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
