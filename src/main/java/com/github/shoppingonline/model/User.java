package com.github.shoppingonline.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "User first name must be not empty.")
    private String firstName;

    @NotBlank(message = "User last name must be not empty.")
    private String lastName;

    @NotBlank(message = "User email must be not empty.")
    private String email;

    @Length(min = 6, message = "User password must be not empty.")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Set<Order> orders;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
