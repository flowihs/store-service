package nocast.storeservice.category.mapper;

import lombok.NonNull;
import nocast.storeservice.category.dto.CategoryBranchDto;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Component
public class CategoryBranchRecursiveMapper implements RecursiveMapper<Category, CategoryBranchDto> {

    @Override
    public CategoryBranchDto map(@NonNull Category object, int depth) {
        return new CategoryBranchDto(
                object.getId(),
                object.getParent() != null && depth > 0
                        ? this.map(object.getParent(), --depth)
                        : null,
                extractCategoryInfo(object.getTranslations()),
                object.getSortOrder(),
                object.getLevel(),
                object.isRoot(),
                object.isLeaf(),
                object.getImage()
        );
    }

    private CategoryInfo extractCategoryInfo(Map<String, CategoryInfo> translations) {
        final var langCode = LocaleContextHolder.getLocale().getLanguage();
        return translations != null
                ? translations.get(langCode)
                : null;
    }
}
