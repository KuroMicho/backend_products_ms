package com.beerproducts.backend_products_ms.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String msg) {
        super(msg);
    }

}