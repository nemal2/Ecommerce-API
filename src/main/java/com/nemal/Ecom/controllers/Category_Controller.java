package com.nemal.Ecom.controllers;
import com.nemal.Ecom.model.Category;
import com.nemal.Ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//@RequestMapping("api")
@RestController
public class Category_Controller {
    //field injection using autowired. (if not ew also can use constructor injection)
    @Autowired
    private CategoryService categoryService;

    //RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    @GetMapping("api/public/categories")
    public ResponseEntity <List <Category>> getAllCategoryList() {

        List<Category> allCategoryList = categoryService.getAllCategoryList();
        return new ResponseEntity<>(allCategoryList, HttpStatus.OK);
    }


    @GetMapping("api/public/categories/{categoryId}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long categoryId) {
        Optional<Category> res = categoryService.readCategory(categoryId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    //@GetMapping("api/admin/categories/{categoryName}")

    @PostMapping("api/admin/categories")
    public ResponseEntity <String> addCategory(@RequestBody Category category) {

        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try{
            String status = categoryService.deleteCategory(categoryId);
            return  new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        try{
            Category savedCategory = categoryService.updateCategory(categoryId, category);
            return  new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}

