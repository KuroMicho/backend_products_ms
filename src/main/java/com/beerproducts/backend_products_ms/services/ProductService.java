package com.beerproducts.backend_products_ms.services;

import java.util.List;
import java.util.Optional;

import com.beerproducts.backend_products_ms.models.Product;
import com.beerproducts.backend_products_ms.repositories.ProductRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findProductsByUsername(String username) {
        return productRepository.findByUsername(username);
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(String id) {
        return productRepository.findById(id);
    }

    public String deleteProductById(String id) {
        productRepository.deleteById(id);
        return "product deleted";
    }
}
