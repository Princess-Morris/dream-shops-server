package com.princess.dream_shops.dto;

import java.math.BigDecimal;
import java.util.List;

import com.princess.dream_shops.model.Category;
import com.princess.dream_shops.model.Image;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    
    private Category category;

    private List<ImageDto> images;

}
