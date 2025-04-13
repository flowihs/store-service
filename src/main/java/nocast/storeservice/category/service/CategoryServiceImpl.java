package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.dto.CategoryReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@RequiredArgsConstructor
@Component
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Page<CategoryReadDto> findAll() {
        return null;
    }

    @Override
    public Page<CategoryReadDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CategoryReadDto> findAll(Pageable pageable, CategoryFilter filter) {
        return null;
    }

    @Override
    public Optional<CategoryReadDto> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public CategoryReadDto create(CategoryCreateDto category) {
        return null;
    }

    @Override
    public Optional<CategoryReadDto> updateById(Integer id, CategoryEditDto category) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
