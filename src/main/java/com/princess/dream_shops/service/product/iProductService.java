package com.princess.dream_shops.service.product;

import java.util.List;

import com.princess.dream_shops.model.Product;

public interface iProductService {
    Product addProduct(Product product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryNameAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductByBrandAndName(String brand, String name);
}
