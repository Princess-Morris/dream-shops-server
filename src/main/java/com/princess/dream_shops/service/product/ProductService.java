package com.princess.dream_shops.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.princess.dream_shops.exceptions.ProductNotFoundException;
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
        return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id){
         productRepository.findById(id)
         .ifPresentOrElse(productRepository::delete, 
         () -> {throw new ProductNotFoundException("Product not found");}); 
    }

    @Override
    public void updateProduct(Product product, Long productId) {
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryName(String category){
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand){
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryNameAndBrand(String category, String brand){
        return productRepository.findByCategoryNameAndBrand(category, brand);
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

}
