package com.backend.rocker.serviceImpl;

import com.backend.rocker.dtos.ProductRequestDTO;
import com.backend.rocker.dtos.ProductResponseDTO;
import com.backend.rocker.repository.ProductRepository;
import com.backend.rocker.services.ProductService;
import com.backend.rocker.utility.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO insertProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);

        if (product.getSku() == null || product.getSku().isBlank()) {
            product.setSku(generateSku());
        }

        product.setCreatedAt(Instant.now());
        product.setActive(
                request.getActive() != null ? request.getActive() : true
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
