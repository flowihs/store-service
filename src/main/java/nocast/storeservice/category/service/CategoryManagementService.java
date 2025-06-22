package nocast.storeservice.category.service;

import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.dto.CategoryReadDto;

import java.util.Optional;

public interface CategoryManagementService {
    CategoryReadDto create(CategoryCreateDto dto);

    boolean delete(Integer id);

    Optional<CategoryReadDto> update(CategoryEditDto dto);
}
