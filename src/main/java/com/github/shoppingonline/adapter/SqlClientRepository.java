package com.github.shoppingonline.adapter;

import com.github.shoppingonline.model.Client;
import com.github.shoppingonline.model.ClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlClientRepository extends ClientRepository, JpaRepository<Client, Integer> {
}
