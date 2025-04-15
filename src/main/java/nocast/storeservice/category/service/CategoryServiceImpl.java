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
    private final Sort defaultSort = by(sortOrder, id);
    private final Pageable defaultPageable = PageRequest.of(0, 20, defaultSort);


    @Override
    public Page<CategoryReadDto> findAll() {
        return null;
    }

    @Override
    public Page<CategoryReadDto> findAll(TreeViewOptions options) {
        return null;
    }

    @Override
    public Page<CategoryReadDto> findAll(TreeViewOptions options, Pageable pageable) {
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
