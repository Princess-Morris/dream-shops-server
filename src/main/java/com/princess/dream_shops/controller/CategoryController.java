package com.princess.dream_shops.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.princess.dream_shops.model.Category;
import com.princess.dream_shops.response.ApiResponse;
import com.princess.dream_shops.service.category.iCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
     private final iCategoryService categoryService;

     @GetMapping("/all")
     public String getMethodName(@RequestParam String param) {
         return new String();
     }
     
     public ResponseEntity<ApiResponse> getAllCategories(){
        try{
             List<Category> categories = categoryService.getAllCategories();
             return ResponseEntity.ok(new ApiResponse("Found!", categories));
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", INTERNAL_SERVER_ERROR));
        }
     }
}
