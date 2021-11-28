package com.beerproducts.backend_products_ms.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import com.beerproducts.backend_products_ms.exceptions.ResourceNotFound;
import com.beerproducts.backend_products_ms.models.Product;
import com.beerproducts.backend_products_ms.services.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> fetchAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> fetchProduct(@PathVariable("id") String id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Not found product with id: " + id));

        return new ResponseEntity<Product>(product, HttpStatus.OK);

    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        try {
            productService.saveOrUpdateProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable("id") String id) {
        Product _product = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Not found product with the id " + id));

        _product.setName(product.getName());
        _product.setDescription(product.getDescription());
        _product.setImage(product.getImage());
        _product.setPrice(product.getPrice());
        _product.setStyle(product.getStyle());
        _product.setCategory(product.getCategory());
        _product.setAvg_grade(product.getAvg_grade());
        _product.setIbu_grade(product.getIbu_grade());

        return new ResponseEntity<>(productService.saveOrUpdateProduct(_product), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Not found product with the id " + id));

        productService.deleteProductById(product.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<Map.Entry<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Set<Map.Entry<String, String>> entrySet = errors.entrySet();
        List<Map.Entry<String, String>> items = new ArrayList<Map.Entry<String, String>>(entrySet);
        Map<String, List<Map.Entry<String, String>>> message = new HashMap<>();
        message.put("errors", items);
        return message;
    }
}
