package com.nemal.Ecom.service;

import com.nemal.Ecom.model.Category;

import java.util.List;

public interface CategoryService {
    List <Category> getAllCategoryList();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Long categoryId, Category category);
}
