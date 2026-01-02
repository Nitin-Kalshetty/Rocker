package com.backend.rocker.utility;

import com.backend.rocker.dtos.ProductRequestDTO;
import com.backend.rocker.dtos.ProductResponseDTO;
import com.backend.rocker.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ProductMapper {

    public Product toEntity(ProductRequestDTO request);

    public ProductResponseDTO toResponse(Product product);
}
