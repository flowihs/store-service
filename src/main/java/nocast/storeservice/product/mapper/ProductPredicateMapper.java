package nocast.storeservice.product.mapper;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.common.components.QPredicates;
import nocast.storeservice.product.exception.LangCodeUndefinedException;
import nocast.storeservice.product.dto.ProductFilter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

import static nocast.storeservice.product.persistence.QProduct.product;

@Component
@RequiredArgsConstructor
public class ProductPredicateMapper implements Mapper<ProductFilter, Predicate> {

    @Override
    public Predicate map(ProductFilter filter) {
        final var langCode = Optional.of(LocaleContextHolder.getLocale())
                .map(Locale::getLanguage)
                .orElseThrow(LangCodeUndefinedException::new);

        return QPredicates.builder()
                .add(filter.getName(), product.name::eq)
                .add(filter.getCategory().getId(), product.categoryId::eq)
                .add(filter.getMinPrice(), product.price::goe)
                .add(filter.getMaxPrice(), product.price::loe)
                .add(filter.getCurrency(), product.currency::eq)
                .add(filter.getBeforeCreateAt(), product.createdAt::before)
                .add(filter.getAfterCreateAt(), product.createdAt::after)
                .add(filter.getPriceType(), product.priceType::eq)
                .add(filter.getDescription(), product.description::eq)
                .build();
    }
}
