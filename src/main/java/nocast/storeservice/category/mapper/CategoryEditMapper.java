package nocast.storeservice.category.mapper;

import nocast.storeservice.category.dto.CategoryEditDto;
import nocast.storeservice.category.exception.LangCodeUndefinedException;
import nocast.storeservice.category.persistence.Category;
import nocast.storeservice.category.persistence.CategoryInfo;
import nocast.storeservice.common.components.Mapper;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryEditMapper implements Mapper<CategoryEditDto, Category> {
    @Override
    public Category map(CategoryEditDto object) {
        Category category = new Category();
        copy(object, category);
        return category;
    }

    @Override
    public Category map(CategoryEditDto from, Category to) {
        copy(from, to);
        return to;
    }

    private void copy (CategoryEditDto from, Category to) {
        final var langCode = Optional.of(LocaleContextHolder.getLocale())
                .map(Locale::getLanguage)
                .orElseThrow(LangCodeUndefinedException::new);
        final var info = to.getTranslations().get(langCode);
        if (from.getName() != null) info.setName(from.getName());
        if (from.getDescription() != null) info.setDescription(from.getDescription());
        if (from.getMetaTitle() != null) info.setMetaTitle(from.getMetaTitle());
        if (from.getMetaDescription() != null) info.setMetaDescription(from.getMetaDescription());
        to.getTranslations().put(langCode, info);
        to.setImage(from.getImage());
        to.setSortOrder(from.getSortOrder());
    }
}
