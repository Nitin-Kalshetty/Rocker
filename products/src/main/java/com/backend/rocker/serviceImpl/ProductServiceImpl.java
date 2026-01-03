package com.backend.rocker.serviceImpl;

import com.backend.rocker.dtos.ProductRequestDTO;
import com.backend.rocker.dtos.ProductResponseDTO;
import com.backend.rocker.model.Product;
import com.backend.rocker.repository.ProductRepository;
import com.backend.rocker.services.ProductService;
import com.backend.rocker.utility.ProductMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);

        if (product.getSku() == null || product.getSku().isBlank()) {
            product.setSku(generateSku());
        }

        product.setCreatedAt(LocalDateTime.now());


        product.setActive(productRequestDTO.getActive() != null ? productRequestDTO.getActive() : true
        );

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return null;
    }

    @Override
    public List<ProductResponseDTO> getProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    private String generateSku() {
        return "SKU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
