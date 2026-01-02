package com.backend.rocker.controller;

import com.backend.rocker.dtos.ProductRequestDTO;
import com.backend.rocker.dtos.ProductResponseDTO;
import com.backend.rocker.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @RequestBody ProductRequestDTO request) {

        ProductResponseDTO response =
                productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
