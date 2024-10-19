package com.ecom.productservice.service;

import com.ecom.productservice.dto.ProductDto;
import com.ecom.productservice.entity.Product;
import com.ecom.productservice.exception.ResourceNotFoundException;
import com.ecom.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public String addProduct(ProductDto productDto) {
        if (productDto==null) {
            throw new ResourceNotFoundException("Object can not be null");
        }
        Product product = Product.converProductDtoToProduct(productDto);
        Product product1 = productRepository.save(product);
        return "Prodcut with productid "+product1.getProductId()+" has been added";
    }

    @Override
    public ProductDto getProduct(String id) {
       Product product =  productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Product avaiable with Productid" +id));
        return Product.converProductToProductDto(product);
    }


    @Override
    public String updateProduct(ProductDto productDto) {
        productRepository.findById(productDto.getProductId()).orElseThrow(()-> new ResourceNotFoundException("No Product avaiable with Productid" +productDto.getProductId()));
        Product product = Product.converProductDtoToProduct(productDto);
        productRepository.save(product);
        return "Product with productid :"+productDto.getProductId()+ "has been updated";
    }

    @Override
    public String deleteProduct(String id) {
        productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Product avaiable with Productid" +id));
        productRepository.deleteById(id);
        return "Product with productid :"+id+ "has been deleted";
    }
}
