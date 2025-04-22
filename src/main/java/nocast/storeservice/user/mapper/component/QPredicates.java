package nocast.storeservice.user.mapper.component;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicates {
    private final List<Predicate> predicates = new ArrayList<>();

    public static nocast.storeservice.user.mapper.component.QPredicates builder() {
        return new nocast.storeservice.user.mapper.component.QPredicates();
    }

    public <T> nocast.storeservice.user.mapper.component.QPredicates add(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public <T> nocast.storeservice.user.mapper.component.QPredicates add(Predicate predicate) {
        predicates.add(predicate);
        return this;
    }

    public Predicate build() {
        return Optional.ofNullable(ExpressionUtils.allOf(predicates))
                .orElseGet(() -> Expressions.asBoolean(true).isTrue());
    }

    public Predicate buildOr() {
        return Optional.ofNullable(ExpressionUtils.anyOf(predicates))
                .orElseGet(() -> Expressions.asBoolean(true).isTrue());
    }
}
