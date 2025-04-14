package nocast.storeservice.category.mapper;

import lombok.NonNull;
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
    public CategoryReadDto map(@NonNull Category object) {
        final var langCode = LocaleContextHolder.getLocale().getLanguage();
        final var parentId = Optional.of(object)
                .map(Category::getParent)
                .map(Category::getId)
                .orElse(null);
        final var parentInfo = Optional.of(object)
                .map(Category::getParent)
                .map(Category::getTranslations)
                .map(it -> it.get(langCode))
                .map(CategoryInfo::getName)
                .orElse(null);
        return new CategoryReadDto(
                object.getId(),
                parentId,
                parentInfo,
                object.getTranslations().get(langCode),
                object.getSortOrder(),
                object.getLevel(),
                object.getImage()
        );
    }
}
