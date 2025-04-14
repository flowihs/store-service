package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.*;
import nocast.storeservice.category.mapper.Mapper;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static nocast.storeservice.category.persistence.Category.Fields.id;
import static nocast.storeservice.category.persistence.Category.Fields.sortOrder;
import static org.springframework.data.domain.Sort.by;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@RequiredArgsConstructor
@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Mapper<Category, CategoryTreeDto> categoryTreeMapper;
    private final Mapper<Category, CategoryBranchDto> categoryBranchMapper;
    private final Mapper<CategoryEditDto, Category> categoryEditMapper;
    private final Mapper<CategoryCreateDto, Category> categoryCreateMapper;
    private final Sort defaultSort = by(sortOrder, id);
    private final Pageable defaultPageable = PageRequest.of(0, 20, defaultSort);


    @Override
    public Page<CategoryTreeDto> findAll() {
        return null;
    }

    @Override
    public Page<CategoryTreeDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CategoryTreeDto> findAll(Pageable pageable, CategoryFilter filter) {
        return null;
    }

    @Override
    public Optional<CategoryBranchDto> findById(final Integer id) {
        return categoryRepository.findById(id).map(categoryBranchMapper::map);
    }

    @Override
    public CategoryBranchDto create(CategoryCreateDto category) {
        Category map = categoryCreateMapper.map(category);
        categoryRepository.save(map);
        return categoryBranchMapper.map(map);
    }

    @Override
    public Optional<CategoryBranchDto> updateById(final Integer id, final CategoryEditDto category) {
        return categoryRepository.findById(id)
                .map(it -> categoryEditMapper.map(category, it))
                .map(categoryRepository::saveAndFlush)
                .map(categoryBranchMapper::map);
    }

    @Override
    public boolean deleteById(final Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
