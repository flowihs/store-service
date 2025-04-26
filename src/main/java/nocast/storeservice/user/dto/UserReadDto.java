package nocast.storeservice.user.dto;

import lombok.Value;

import java.util.List;


@Value
public class UserReadDto {
     Long id;
     String username;
     String firstName;
     List<String> roles;
}
