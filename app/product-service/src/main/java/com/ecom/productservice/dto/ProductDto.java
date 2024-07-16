package com.ecom.productservice.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String productId;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
}
