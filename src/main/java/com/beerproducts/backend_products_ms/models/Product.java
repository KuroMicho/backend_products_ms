package com.beerproducts.backend_products_ms.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String username;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotEmpty(message = "Category is required")
    private List<String> category;

    @NotBlank(message = "Image is required")
    private String image;
    @PositiveOrZero

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotBlank(message = "Style is required")
    private String style;

    @PositiveOrZero
    @NotNull(message = "AVG grade is required")
    private Float avg_grade;

    @PositiveOrZero
    @NotNull(message = "IBU grade is required")
    private Float ibu_grade;

    @CreatedDate
    private Date at_created;

    @LastModifiedDate
    private Date at_modified;

    public Product(String name, String username, String description, List<String> category, String image,
            BigDecimal price, String style,
            Float avg_grade, Float ibu_grade) {
        this.name = name;
        this.username = username;
        this.description = description;
        this.category = category;
        this.image = image;
        this.style = style;
        this.price = price;
        this.avg_grade = avg_grade;
        this.ibu_grade = ibu_grade;
    }
}
