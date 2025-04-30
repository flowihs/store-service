package nocast.storeservice.user.mapper;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.common.components.Mapper;
import nocast.storeservice.user.dto.UserCreateDto;
import nocast.storeservice.user.persistence.User;
import nocast.storeservice.user.provider.RoleProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    private final RoleProvider roleProvider;

    @Override
    public User map(UserCreateDto object) {
        return User.builder()
                .username(object.getUsername())
                .password(object.getPassword())
                .email(object.getEmail())
                .firstName(object.getFirstName())
                .phoneNumber(object.getPhoneNumber())
                .roles(roleProvider.getRoles())
                .build();
    }
}
