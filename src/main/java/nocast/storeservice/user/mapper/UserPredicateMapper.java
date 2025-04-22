package nocast.storeservice.user.mapper;

import nocast.storeservice.user.dto.UserFilter;
import nocast.storeservice.user.mapper.component.QPredicates;
import nocast.storeservice.user.persistence.QUser;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;
import static nocast.storeservice.user.persistence.QUser.user;

@Component
public class UserPredicateMapper {
    public Predicate map(UserFilter userFilter) {
        return QPredicates.builder()
                .add(userFilter.getEmail(), user.email::eq)
                .add(userFilter.getUsername(), user.username::eq)
                .add(userFilter.getFirstName(), user.firstName::eq)
                .add(userFilter.getPhoneNumber(), user.phoneNumber::eq)
                .build();
    }
}
