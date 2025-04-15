package nocast.storeservice.category.mapper;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.exception.LangCodeUndefinedException;
import nocast.storeservice.category.mapper.component.QPredicates;
import nocast.storeservice.category.persistence.QCategory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Component
public class CategoryPredicateMapper implements Mapper<CategoryFilter, Predicate> {

    @Override
    public Predicate map(CategoryFilter filter) {
        final var landCode = Optional.of(LocaleContextHolder.getLocale())
                .map(Locale::getLanguage)
                .orElseThrow(LangCodeUndefinedException::new);
        final var langCode = LocaleContextHolder.getLocale().getLanguage();
        return QPredicates.builder()
                .add(filter.getLevel(), QCategory.category.level::eq)
                .add(filter.getParentId(), QCategory.category.parent.id::eq)
                .add(filter.getName(), name -> buildNamePredicate(langCode, name))
                .build();
    }

    private Predicate buildNamePredicate(String langCode, String name) {
        if (langCode == null || name == null) {
            return Expressions.TRUE;
        }
         return Expressions.stringTemplate(
             "{0}->>'{1}'->>'name' ILIKE {2}",
             QCategory.category.translations,
             langCode,
             "%" + name + "%"
         ).isNotNull();
    }
}
