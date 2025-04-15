package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.dto.TreeViewOptions;
import nocast.storeservice.category.mapper.CategoryPredicateMapper;
import nocast.storeservice.category.mapper.CategoryReadMapper;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    private final CategoryReadMapper categoryReadMapper;
    private final CategoryPredicateMapper categoryPredicateMapper;
    private final TreeViewOptions defaultTreeViewOptions = new TreeViewOptions(Integer.MAX_VALUE, 1);
    private final Sort defaultSort = by(sortOrder, Category.Fields.id);
    private final Pageable defaultPageable = PageRequest.of(0, 20, defaultSort);


    @Override
    public Page<CategoryReadDto> findAll() {
        return this.findAll(defaultTreeViewOptions, defaultPageable, new CategoryFilter(0, null, null));
    }

    @Override
    public Page<CategoryReadDto> findAll(TreeViewOptions options) {
        return this.findAll(options, defaultPageable, new CategoryFilter(0, null, null));
    }

    @Override
    public Page<CategoryReadDto> findAll(TreeViewOptions options, Pageable pageable) {
        return this.findAll(options, pageable, new CategoryFilter(0, null, null));
    }

    @Override
    public Page<CategoryReadDto> findAll(TreeViewOptions options, Pageable pageable, CategoryFilter filter) {
        return categoryRepository.findAllByActive(true, pageable, categoryPredicateMapper.map(filter))
                .map(it -> categoryReadMapper.map(it, options.getBranchDepth(), options.getTreeDepth()));
    }

    @Override
    public Optional<CategoryReadDto> findById(Integer id) {
        return categoryRepository.findByIdAndActive(id, true)
                .map(it -> categoryReadMapper.map(
                        it,
                        defaultTreeViewOptions.getBranchDepth(),
                        defaultTreeViewOptions.getTreeDepth()
                ));
    }
}
