package com.backend.rocker.utility;

import com.backend.rocker.dtos.CategoryRequestDTO;
import com.backend.rocker.dtos.CategoryResponseDTO;
import com.backend.rocker.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    Category toEntity(CategoryRequestDTO categoryRequestDTO);

    @Mapping(target = "parentId", source = "parent.id")
    CategoryResponseDTO toResponseDTO(Category category);
}
