package com.beerproducts.backend_products_ms.repositories;

import java.util.List;

import com.beerproducts.backend_products_ms.models.Order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{'username': ?0}")
    List<Order> findByUsername(String username);
}
