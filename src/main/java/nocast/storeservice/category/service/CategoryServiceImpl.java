package nocast.storeservice.category.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.mapper.Mapper;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static nocast.storeservice.category.persistence.Category.Fields.*;
import static org.springframework.data.domain.Sort.by;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@RequiredArgsConstructor
@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Mapper<Category, CategoryReadDto> categoryReadMapper;
    private final Sort defaultSort = by(level, parent, sortOrder, id);
    private final Pageable defaultPageable = PageRequest.of(0, 50, defaultSort);


    @Override
    public Page<CategoryReadDto> findAll() {
        return categoryRepository.findAll(defaultPageable)
                .map(categoryReadMapper::map);
    }

    @Override
    public Page<CategoryReadDto> findAll(@NonNull Pageable pageable) {
        return Optional.of(pageable)
                .map(it -> it.getSort().isSorted()
                        ? it
                        : PageRequest.of(it.getPageNumber(), it.getPageSize())
                )
                .map(categoryRepository::findAll)
                .orElse(Page.empty())
                .map(categoryReadMapper::map);
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
