package com.backend.rocker.serviceImpl;

import com.backend.rocker.dtos.CategoryRequestDTO;
import com.backend.rocker.dtos.CategoryResponseDTO;
import com.backend.rocker.model.Category;
import com.backend.rocker.repository.CategoryRepository;
import com.backend.rocker.services.CategoryService;
import com.backend.rocker.utility.CategoryMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(final CategoryRepository categoryRepository, final CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setActive(categoryRequestDTO.getActive() != null ? categoryRequestDTO.getActive() : true);

        // Handle parent category (self reference)
        if (categoryRequestDTO.getParentId() != null) {
            Category parent = categoryRepository.findById(categoryRequestDTO.getParentId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Parent category not found"));
            category.setParent(parent);
        }

        Category saved = categoryRepository.save(category);

        return categoryMapper.toResponseDTO(saved);
    }
}
