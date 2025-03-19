package com.nemal.Ecom.service;

import com.nemal.Ecom.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List <Category> getAllCategoryList();
    void createCategory(Category category);
    Category readCategory(String categoryName);
    Optional<Category> readCategory(Long categoryId);
    String deleteCategory(Long categoryId);
    Category updateCategory(Long categoryId, Category category);
}
