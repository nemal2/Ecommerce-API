package com.nemal.Ecom.dto.product;


import com.nemal.Ecom.model.Product;
import jakarta.validation.constraints.NotNull;

public class ProductDto {
    private Long id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull String price;
    private @NotNull String description;
    private @NotNull Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public ProductDto(@NotNull String name, @NotNull String imageURL,@NotNull String price,@NotNull String description,@NotNull Long categoryId) {
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.imageURL = product.getImageURL();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.categoryId = product.getCategory().getCategoryId();
    }

    public ProductDto() {
    }
}
