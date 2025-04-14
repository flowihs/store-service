package nocast.storeservice.category.mapper;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreateMapper implements Mapper<CategoryCreateDto, Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category map(CategoryCreateDto object) {
        final var parent = categoryRepository.findById(object.getParentId())
                // TODO CategoryNotFoundException
                .orElseThrow();

        return Category.builder()
                .parent(parent)
                .build();
    }
}
