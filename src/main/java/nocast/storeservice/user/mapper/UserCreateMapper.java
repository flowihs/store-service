package nocast.storeservice.user.mapper;

import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.user.dto.TypeRole;
import nocast.storeservice.user.dto.UserCreateDto;
import nocast.storeservice.user.persistence.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    @Override
    public User map(UserCreateDto object) {
        return new User(
                object.getUsername(),
                object.getPassword(),
                object.getEmail(),
                object.getFirstName(),
                object.getPhoneNumber(),
                List.of(TypeRole.ROLE_USER.toString())
        );
    }
}
