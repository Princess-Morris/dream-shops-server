package com.princess.dream_shops.service.category;

import java.util.List;

import com.princess.dream_shops.model.Category;

public class CategoryService implements iCategoryService {

    @Override
   public Category getCategoryById(Long id){
        return null;
    }

    @Override
    public Category getCategoryByName(String name){
        return null;
    }

    @Override
    public List<Category>getAllCategories(){
        return List.of();
    }

    public Category addCategory(Category category){
         return null;
    }

    public Category updateCategory(Category category){
        return null;
    }

    public void deleteCategoryById(Long id){
    }
}
