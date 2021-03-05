package com.github.shoppingonline.adapter;

import com.github.shoppingonline.model.OrderDetails;
import com.github.shoppingonline.model.OrderDetailsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlOrderDetailsRepository extends OrderDetailsRepository, JpaRepository<OrderDetails, Integer> {

}
