package com.nemal.Ecom.service;

import com.nemal.Ecom.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categoryList = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategoryList() {
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryList.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryList.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found "));

        categoryList.remove(category);
        return "Deleted category id = " + categoryId + "\n";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Optional<Category> optionalCategory = categoryList.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if (optionalCategory.isPresent()) {
            category.setCategoryId(categoryId);
            categoryList.remove(optionalCategory.get());
            categoryList.add(category);
            return category;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found");
        }

    }
}
