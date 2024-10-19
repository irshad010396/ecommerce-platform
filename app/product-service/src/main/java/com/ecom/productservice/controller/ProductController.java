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
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("addproduct")
    public ResponseEntity<String> addProduct(@RequestBody  ProductDto productDto) {
        log.info("request received for adding product with productId :"+productDto.getProductId());
        String message = productService.addProduct(productDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("getproduct")
    public ResponseEntity<ProductDto> getProduct(@RequestParam String id) {
        log.info("request received for fetching product details wit id :"+id);
        ProductDto productDto = productService.getProduct(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PutMapping("updateproduct")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto) {
        log.info("request received for updating product details with productId :"+productDto.getProductId());
        String message = productService.updateProduct(productDto);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("deleteproduct")
    public ResponseEntity<String> deleteProdcut(@RequestParam String id) {
        log.info("request received for deleting product details with id :"+id);
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
