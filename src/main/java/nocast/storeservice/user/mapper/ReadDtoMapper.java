package nocast.storeservice.user.mapper;

import nocast.storeservice.user.dto.ReadUserDto;
import nocast.storeservice.user.persistence.User;
import org.springframework.stereotype.Component;

@Component
public class ReadDtoMapper {
    public ReadUserDto map(User object) {
        return new ReadUserDto(
                object.getId(),
                object.getUsername(),
                object.getFirstName(),
                object.getEmail(),
                object.getPhoneNumber(),
                object.getRoles().toString());
    }
}
