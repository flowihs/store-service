package nocast.storeservice.category.mapper;

import nocast.storeservice.category.dto.CategoryCreateDto;
import nocast.storeservice.category.exception.LangCodeUndefinedException;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import nocast.storeservice.common.components.Mapper;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CategoryCreateMapper implements Mapper<CategoryCreateDto, Category> {

    @Override
    public Category map(CategoryCreateDto object) {
        final var langCode = Optional.of(LocaleContextHolder.getLocale())
                .map(Locale::getLanguage)
                .orElseThrow(LangCodeUndefinedException::new);
        Map<String, CategoryInfo> translations = new HashMap<>();
        translations.put(langCode, new CategoryInfo(
                object.getName(),
                object.getDescription(),
                object.getMetaTitle(),
                object.getMetaDescription()));
        return Category.builder()
                .active(object.isActive())
                .image(object.getImage())
                .level(object.getLevel())
                .isLeaf(object.isLeaf())
                .isRoot(object.isRoot())
                .sortOrder(object.getSortOrder())
                .translations(translations)
                .build();
    }
}
