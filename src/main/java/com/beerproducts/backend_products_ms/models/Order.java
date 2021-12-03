package com.beerproducts.backend_products_ms.models;

import java.util.Date;

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
    @NotNull(message = "Product Id is required")
    private String productId;
    @PositiveOrZero
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    @ValueOfEnum(enumClass = OrderStatus.class)
    private String status;
    @CreatedDate
    private Date at_created;

    public Order(String productId, Integer quantity, String status) {
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }
}
