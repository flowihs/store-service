package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.dto.TreeViewOptions;
import nocast.storeservice.category.mapper.CategoryEditMapper;
import nocast.storeservice.category.mapper.CategoryReadMapper;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.repository.CategoryRepository;
import nocast.storeservice.common.components.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryManagementServiceImpl implements CategoryManagementService {
    private final Mapper<CategoryCreateDto, Category> categoryMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryReadMapper categoryReadMapper;
    private final TreeViewOptions defaultTreeViewOptions = new TreeViewOptions(Integer.MAX_VALUE, 1);
    private final CategoryEditMapper categoryEditMapper;

    @Override
    public CategoryReadDto create(CategoryCreateDto dto) {
        return Optional.ofNullable(dto)
                .map(categoryMapper::map)
                .map(categoryRepository::saveAndFlush)
                .map(it -> categoryReadMapper.map(it, defaultTreeViewOptions.getBranchDepth(), defaultTreeViewOptions.getTreeDepth()))
                .orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CategoryReadDto> update(CategoryEditDto dto) {
        return Optional.ofNullable(dto)
                .map(it -> categoryRepository.findById(it.getId()))
                .map(it -> categoryEditMapper.map(dto, it.get()))
                .map(categoryRepository::saveAndFlush)
                .map((Category saved) -> categoryReadMapper.map(
                        saved,
                        defaultTreeViewOptions.getBranchDepth(),
                        defaultTreeViewOptions.getTreeDepth()
                ));
    }
}
