package nocast.storeservice.category.mapper;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.dto.CategoryTreeDto;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Component
@RequiredArgsConstructor
public class CategoryTreeMapper implements Mapper<Category, CategoryTreeDto> {

    @Override
    public CategoryTreeDto map(Category category) {
        return new CategoryTreeDto(
                category.getId(),
                category.getParent() != null ? category.getParent().getId() : null,
                extractCategoryInfo(category.getTranslations()),
                category.getSortOrder(),
                category.getLevel(),
                category.getImage(),
                mapSubcategories(category.getSubcategories())
        );
    }

    private List<CategoryTreeDto> mapSubcategories(List<Category> subcategories) {
        if (subcategories != null) {
            return subcategories.stream()
                    .sorted(Comparator.comparingInt(Category::getSortOrder))
                    .map(this::map)
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    private CategoryInfo extractCategoryInfo(Map<String, CategoryInfo> translations) {
        final var langCode = LocaleContextHolder.getLocale().getLanguage();
        return translations != null
                ? translations.get(langCode)
                : null;
    }
}
