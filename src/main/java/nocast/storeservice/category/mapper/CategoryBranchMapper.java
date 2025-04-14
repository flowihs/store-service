package nocast.storeservice.category.mapper;

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
public class CategoryBranchMapper implements Mapper<Category, CategoryBranchDto> {

    @Override
    public CategoryBranchDto map(Category object) {
        return new CategoryBranchDto(
                object.getId(),
                object.getParent() != null ? this.map(object.getParent()) : null,
                extractCategoryInfo(object.getTranslations()),
                object.getSortOrder(),
                object.getLevel(),
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
