package nocast.storeservice.user.mapper;

import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.common.components.QPredicates;
import nocast.storeservice.user.dto.UserFilter;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;

import static nocast.storeservice.user.persistence.QUser.user;


@Component
public class UserPredicateMapper implements Mapper<UserFilter, Predicate> {
    public Predicate map(UserFilter userFilter) {
        return QPredicates.builder()
                .add(userFilter.getUsername(), user.username::contains)
                .add(userFilter.getFirstName(), user.firstName::contains)
                .build();
    }
}
