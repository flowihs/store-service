package nocast.storeservice.user.dto;

import lombok.Value;

@Value
public class UserCreateDto {
    String username;
    String password;
    String email;
    String firstName;
    String phoneNumber;
}
