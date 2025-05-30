package com.princess.dream_shops.service.category;

import java.util.List;

import com.princess.dream_shops.model.Category;

public interface iCategoryService {

    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);
}
