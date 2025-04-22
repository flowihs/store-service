package nocast.storeservice.user.dto;

import lombok.Value;

import java.util.List;


@Value
public class ReadUserDto {
     Long id;
     String username;
     String firstName;
     String email;
     int phoneNumber;
     String roles;
}
