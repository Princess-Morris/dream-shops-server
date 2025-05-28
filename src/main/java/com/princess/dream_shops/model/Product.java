package com.princess.dream_shops.model;

import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String brand;
    private String BigDecimal;
    private int inventory;
    private String description;
    
    private Category cateory;
    private List<Image> images;
}
