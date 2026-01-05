package com.backend.rocker.services;

import com.backend.rocker.dtos.CategoryRequestDTO;
import com.backend.rocker.dtos.CategoryResponseDTO;

public interface CategoryService {

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
}
