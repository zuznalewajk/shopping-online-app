package com.github.shoppingonline.model;

public interface UserRepository {

    User findByEmail(String email);
    User save(User user);
}
