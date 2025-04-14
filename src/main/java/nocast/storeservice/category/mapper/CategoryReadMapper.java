package nocast.storeservice.category.mapper;

import nocast.storeservice.category.dto.CategoryReadDto;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Component
public class CategoryReadMapper implements Mapper<Category, CategoryReadDto> {

    @Override
    public CategoryReadDto map(Category object) {
        final var langCode = LocaleContextHolder.getLocale().getLanguage();
        final var parentInfo = object.getParent().getTranslations().get(langCode);
        return new CategoryReadDto(
                object.getId(),
                object.getParent().getId(),
                Optional.ofNullable(parentInfo)
                        .map(CategoryInfo::getName)
                        .orElse(null),
                object.getTranslations().get(langCode),
                object.getSortOrder(),
                object.getLevel(),
                object.getImage()
        );
    }
}
