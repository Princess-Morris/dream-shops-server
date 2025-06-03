package com.princess.dream_shops.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.princess.dream_shops.exceptions.ResourceNotFoundException;
import com.princess.dream_shops.model.Product;
import com.princess.dream_shops.request.AddProductRequest;
import com.princess.dream_shops.request.ProductUpdateRequest;
import com.princess.dream_shops.response.ApiResponse;
import com.princess.dream_shops.service.product.iProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
   private final iProductService productService;

   @GetMapping("/all")
   public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("success", products));
   }

   @GetMapping("/product/{productId}/product")
   public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
    try{
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(new ApiResponse("succes", product));
   
    } catch (ResourceNotFoundException e ){
         return ResponseEntity.status(NOT_FOUND).body(new ApiResponse((e.getMessage()), null));
    }
   }

   @PostMapping("/add")
   public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
    try{
        Product theProduct = productService.addProduct(product);
        return ResponseEntity.ok(new ApiResponse("Add product success!", theProduct));
    } catch(Exception e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
    }
   }

   @PutMapping("/product/{productId}/update")
   public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId){
    try{
        Product theProduct = productService.updateProduct(request, productId);
        return ResponseEntity.ok(new ApiResponse("update product success!", theProduct));
    } catch( ResourceNotFoundException e){
       return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
   
   }

   @DeleteMapping("/product/{productId}/delete")
   public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
    try{
        productService.deleteProductById(productId);
        return ResponseEntity.ok(new ApiResponse("delete success!", productId));
    } catch (ResourceNotFoundException e){
         return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
    
   }

   @GetMapping("/products/by/brand-and-name")
   public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
    try{
        List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
        if (products.isEmpty()){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
        }
        return ResponseEntity.ok(new ApiResponse("success!", products));
    } catch (Exception e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
    }
    }

    @GetMapping("/products/by/category-and-brand")
   public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
    try{
        List<Product> products = productService.getProductsByCategoryNameAndBrand(category, brand);
        if (products.isEmpty()){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
        }
        return ResponseEntity.ok(new ApiResponse("success!", products));
    } catch (Exception e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
    }
    }

    @GetMapping("/products/{name}/products")
   public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name){
    try{
        List<Product> products = productService.getProductsByName(name);
        if (products.isEmpty()){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
        }
        return ResponseEntity.ok(new ApiResponse("success!", products));
    } catch (Exception e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
    }
    }

    @GetMapping("/products/{brand}/products")
   public ResponseEntity<ApiResponse> findProductByName(@PathVariable String brand){
    try{
        List<Product> products = productService.getProductsByName(brand);
        if (products.isEmpty()){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
        }
        return ResponseEntity.ok(new ApiResponse("success!", products));
    } catch (Exception e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
    }
    }

    @GetMapping("/products/{category}/all/products")
   public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category){
    try{
        List<Product> products = productService.getProductsByCategoryName(category);
        if (products.isEmpty()){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
        }
        return ResponseEntity.ok(new ApiResponse("success!", products));
    } catch (Exception e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
    }
    }
    
}
