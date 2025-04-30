package nocast.storeservice.user.provider;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleProvider {
    List<String> getRoles();
}
