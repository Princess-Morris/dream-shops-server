package com.princess.dream_shops.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
       super(message);
    }
}
