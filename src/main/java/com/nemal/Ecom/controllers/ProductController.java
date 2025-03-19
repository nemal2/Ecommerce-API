package com.nemal.Ecom.controllers;

import com.nemal.Ecom.common.ApiResponse;
import com.nemal.Ecom.dto.product.ProductDto;
import com.nemal.Ecom.model.Category;
import com.nemal.Ecom.model.Product;
import com.nemal.Ecom.service.CategoryService;
import com.nemal.Ecom.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/api/admin/products")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }


    @GetMapping("api/public/products")
    public ResponseEntity<List<ProductDto>> getAllProductList() {
        List<ProductDto> allProductList = productService.listProducts();
        return new ResponseEntity<>(allProductList, HttpStatus.OK);
    }

    @PutMapping("api/admin/products/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        Optional<Category> category = categoryService.readCategory(productDto.getCategoryId());
        productService.updateProduct(productId, productDto, category.orElse(null));
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }
}
