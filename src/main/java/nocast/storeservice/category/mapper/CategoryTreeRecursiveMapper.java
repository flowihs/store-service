package nocast.storeservice.category.mapper;

import lombok.NonNull;
import nocast.storeservice.category.dto.CategoryTreeDto;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Component
public class CategoryTreeRecursiveMapper implements RecursiveMapper<Category, CategoryTreeDto> {

    @Override
    public CategoryTreeDto map(@NonNull Category object, int depth) {
        return new CategoryTreeDto(
                object.getId(),
                object.getParent() != null ? object.getParent().getId() : null,
                extractCategoryInfo(object.getTranslations()),
                mapSubcategories(object.getSubcategories(), depth),
                object.getSortOrder(),
                object.getLevel(),
                object.isRoot(),
                object.isLeaf(),
                object.getImage()
        );
    }

    private List<CategoryTreeDto> mapSubcategories(List<Category> subcategories, int depth) {
        if (subcategories == null || depth < 1) {
            return Collections.emptyList();
        }
        return subcategories.stream()
                .sorted(comparingInt(Category::getSortOrder))
                .map(it -> this.map(it, depth - 1))
                .toList();
    }

    private CategoryInfo extractCategoryInfo(Map<String, CategoryInfo> translations) {
        final var langCode = LocaleContextHolder.getLocale().getLanguage();
        return translations != null
                ? translations.get(langCode)
                : null;
    }
}
