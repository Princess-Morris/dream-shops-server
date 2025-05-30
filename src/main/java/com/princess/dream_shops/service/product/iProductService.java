package com.princess.dream_shops.service.product;

import java.util.List;

import com.princess.dream_shops.model.Product;
import com.princess.dream_shops.request.AddProductRequest;
import com.princess.dream_shops.request.ProductUpdateRequest;

public interface iProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryNameAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductByBrandAndName(String brand, String name);
}
