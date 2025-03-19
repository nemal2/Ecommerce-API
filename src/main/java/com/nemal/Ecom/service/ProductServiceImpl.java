package com.nemal.Ecom.service;

import com.nemal.Ecom.dto.product.ProductDto;
import com.nemal.Ecom.model.Category;
import com.nemal.Ecom.model.Product;
import com.nemal.Ecom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        product.setCategory(category);
        return product;
    }

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    //list of all products
    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(new ProductDto(product));
        }

        return productDtos;
    }

    //update product
    public void updateProduct(Long productId, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        product.setId(productDto.getId());
        productRepository.save(product);
    }
}
