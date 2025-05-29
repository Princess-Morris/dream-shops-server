package com.princess.dream_shops.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.princess.dream_shops.model.Product;
import com.princess.dream_shops.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements iProductService {

    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product){
        return null;
    }

    @Override
    public Product getProductById(Long id){
        return null;
    }

    @Override
    public void deleteProductBtId(Long id){
    }

    @Override
    public List<Product> getAllProducts(){
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String category){
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrand(String brand){
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand){
        return List.of();
    }

    @Override
    public List<Product> getProductsByName(String name){
          return List.of();
    }

    @Override
    public List<Product> getProductsByBrandAndName(String category, String name){
        return List.of();
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name){
        return null;
    }

    @Override
    public void updateProduct(Product product, Long productId) {
    }

}
