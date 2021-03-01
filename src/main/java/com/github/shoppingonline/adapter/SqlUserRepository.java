package com.github.shoppingonline.adapter;

import com.github.shoppingonline.model.User;
import com.github.shoppingonline.model.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {
}
