package com.beerproducts.backend_products_ms.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import com.beerproducts.backend_products_ms.exceptions.ResourceNotFound;
import com.beerproducts.backend_products_ms.models.Order;
import com.beerproducts.backend_products_ms.models.Product;
import com.beerproducts.backend_products_ms.services.OrderService;
import com.beerproducts.backend_products_ms.services.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @PostMapping("/orders")
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order) {

        Product product = productService.findProductById(order.getProductId()).orElse(null);

        if (product == null) {
            throw new ResourceNotFound("Not found product with id " + order.getProductId());
        }

        order.setStatus("pending");

        try {
            orderService.saveOrUpdateOrder(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
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
