package com.beerproducts.backend_products_ms.repositories;

import java.util.List;

import com.beerproducts.backend_products_ms.models.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{'username': ?0}")
    List<Product> findProductsByUsername(String username);
}
