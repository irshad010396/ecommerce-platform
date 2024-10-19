package com.ecom.productservice.entity;

import com.ecom.productservice.dto.ProductDto;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product")
@NoArgsConstructor
public class Product {

    @Id
    private String productId;
    @Nonnull
    private String name;
    @Nonnull
    private String description;
    @Nonnull
    private double price;
    @Nonnull
    private int quantity;
    @Nonnull
    private String category;

    public static Product converProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
//        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        return product;
    }

    public static ProductDto converProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setDescription(product.getDescription());
        return productDto;
    }

}
