package com.beerproducts.backend_products_ms.services;

import java.util.List;
import java.util.Optional;

import com.beerproducts.backend_products_ms.models.Order;
import com.beerproducts.backend_products_ms.repositories.OrderRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findOrdersByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public Order saveOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> findOrderById(String id) {
        return orderRepository.findById(id);
    }

    public String deleteOrderById(String id) {
        orderRepository.deleteById(id);
        return "order cancelled";
    }
}
