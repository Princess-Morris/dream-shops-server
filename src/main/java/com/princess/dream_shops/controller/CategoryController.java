package com.princess.dream_shops.controller;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.princess.dream_shops.exceptions.AlreadyExistsException;
import com.princess.dream_shops.exceptions.ResourceNotFoundException;
import com.princess.dream_shops.model.Category;
import com.princess.dream_shops.response.ApiResponse;
import com.princess.dream_shops.service.category.iCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



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

     @PostMapping("/add")
     public ResponseEntity<ApiResponse> addCategory(@RequestBody Category categry){
        try{
            Category theCategory = categoryService.addCategory(categry);
            return ResponseEntity.ok(new ApiResponse("Success", theCategory));

        } catch (AlreadyExistsException e){
           return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
     }

     @GetMapping("/category/{id}/category")
     public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try{
           Category theCategory = categoryService.getCategoryById(id);
           return ResponseEntity.ok(new ApiResponse("Found", theCategory));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
     }

     @GetMapping("/{name}/category")
     public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        try{
           Category theCategory = categoryService.getCategoryByName(name);
           return ResponseEntity.ok(new ApiResponse("Found", theCategory));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
     }
}
