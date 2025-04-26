package nocast.storeservice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilter {
    String username;
    String firstName;
    String email;
    String phoneNumber;
}
