package com.beerproducts.backend_products_ms.models;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.beerproducts.backend_products_ms.exceptions.ValueOfEnum;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    @NotNull(message = "productId is required")
    private String productId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @PositiveOrZero
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @ValueOfEnum(enumClass = OrderStatus.class)
    private String status;

    @CreatedDate
    private String at_created;

    public Order(String productId, String name, String username, Integer quantity, String status) {
        this.name = name;
        this.username = username;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }
}
