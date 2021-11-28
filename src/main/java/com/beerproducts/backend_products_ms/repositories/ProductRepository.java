package com.beerproducts.backend_products_ms.repositories;

import java.util.Optional;

import com.beerproducts.backend_products_ms.models.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findProductByName(String name);
}
