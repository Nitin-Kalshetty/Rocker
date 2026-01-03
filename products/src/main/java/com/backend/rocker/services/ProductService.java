package com.backend.rocker.services;

import com.backend.rocker.dtos.ProductRequestDTO;
import com.backend.rocker.dtos.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    public List<ProductResponseDTO> getAllProducts();

    public List<ProductResponseDTO> getProduct(ProductRequestDTO productRequestDTO);
}
