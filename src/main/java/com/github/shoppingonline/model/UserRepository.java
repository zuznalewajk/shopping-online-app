package com.github.shoppingonline.model;

public interface UserRepository {

    public User findByEmail(String email);
    public User save(User user);
}
