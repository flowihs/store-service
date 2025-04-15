package nocast.storeservice.category.mapper;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.exception.LangCodeUndefinedException;
import nocast.storeservice.category.mapper.component.QPredicates;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

import static nocast.storeservice.category.persistence.QCategory.category;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Component
public class CategoryPredicateMapper implements Mapper<CategoryFilter, Predicate> {

    @Override
    public Predicate map(CategoryFilter filter) {
        final var langCode = Optional.of(LocaleContextHolder.getLocale())
                .map(Locale::getLanguage)
                .orElseThrow(LangCodeUndefinedException::new);
        return QPredicates.builder()
                .add(filter.getLevel(), category.level::eq)
                .add(filter.getIsRoot(), category.isRoot::eq)
                .add(filter.getIsLeaf(), category.isLeaf::eq)
                .add(filter.getParentId(), category.parent.id::eq)
                .add(filter.getName(), name -> buildNamePredicate(langCode, name))
                .add(filter.getIsActive(), category.active::eq)
                .build();
    }

    private Predicate buildNamePredicate(String langCode, String name) {
        if (langCode == null || name == null) {
            return null;
        }
//        return Expressions.stringTemplate(
//                "{0}->%s->>'name' ILIKE %s".formatted(langCode, "%" + name + "%"),
//                category.translations
//        ).isNotNull();
        return Expressions.booleanTemplate(
                "cast(function('jsonb_extract_path_text', {0}, {1}, 'name') as text) ILIKE {2}",
                category.translations,
                langCode,
                "%" + name + "%"
        );
    }
}
