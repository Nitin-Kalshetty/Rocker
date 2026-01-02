package com.backend.rocker.services;

import com.backend.rocker.dtos.ProductRequestDTO;
import com.backend.rocker.dtos.ProductResponseDTO;

public interface ProductService {

    public ProductResponseDTO insertProduct(ProductRequestDTO productRequestDTO);

    public List<ProductResponseDTO> getAllProducts();

    public List<ProductResponseDTO> getProduct(ProductRequestDTO productRequestDTO);
}
