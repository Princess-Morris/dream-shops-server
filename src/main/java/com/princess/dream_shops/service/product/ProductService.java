package com.princess.dream_shops.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.princess.dream_shops.exceptions.ProductNotFoundException;
import com.princess.dream_shops.model.Category;
import com.princess.dream_shops.model.Product;
import com.princess.dream_shops.repository.CategoryRepository;
import com.princess.dream_shops.repository.ProductRepository;
import com.princess.dream_shops.request.AddProductRequest;
import com.princess.dream_shops.request.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements iProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request){
        // check if the category is found in the DB
        // if Yes, set it as the new product category
        // if no, save it as a new category
        // then set it as the new product category

       Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
       .orElseGet(() -> {
        Category newCategory = new Category(request.getCategory().getName());
        return categoryRepository.save(newCategory);
       });
       request.setCategory(category);
       return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category){
        return new Product(
            request.getName(),
            request.getBrand(),
            request.getPrice(),
            request.getInventory(),
            request.getDescription(),
            category
        );
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
    public Product updateProduct(ProductUpdateRequest product, Long productId) {
       return productRepository.findById(productId)
               .map(existingProduct -> updateExistingProduct(existingProduct, product))
               .map(productRepository :: save)
               .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory((request.getInventory()));
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
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
          return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name){
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name){
        return productRepository.countByBrandAndName(brand, name);
    }

}
