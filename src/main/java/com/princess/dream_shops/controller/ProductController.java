package com.princess.dream_shops.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.princess.dream_shops.exceptions.ResourceNotFoundException;
import com.princess.dream_shops.model.Product;
import com.princess.dream_shops.response.ApiResponse;
import com.princess.dream_shops.service.product.iProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
}
