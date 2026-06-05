package com.ecom.productservice.controller;

import com.ecom.productservice.dto.ProductDto;
import com.ecom.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
        log.info("Request received for adding product with name={}", productDto.getName());
        String message = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id) {
        log.info("Request received for fetching product details for id={}", id);
        ProductDto productDto = productService.getProduct(id);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        log.info("Request received for updating product id={}", id);
        productDto.setProductId(id);
        String message = productService.updateProduct(productDto);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProdcut(@PathVariable String id) {
        log.info("Request received for deleting product id={}", id);
        String message = productService.deleteProduct(id);
        return ResponseEntity.ok(message);
    }
}
