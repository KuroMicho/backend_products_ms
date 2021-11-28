package com.beerproducts.backend_products_ms.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Map<String, List<String>>> responseEntity(ResourceNotFound ex, WebRequest request) {

        List<String> message = new ArrayList<String>(Arrays.asList(ex.getMessage()));
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("errors", message);

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
