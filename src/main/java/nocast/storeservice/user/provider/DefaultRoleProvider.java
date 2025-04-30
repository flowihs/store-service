package nocast.storeservice.user.provider;

import nocast.storeservice.user.dto.TypeRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultRoleProvider implements RoleProvider {
    @Override
    public List<String> getRoles() {
        return List.of(TypeRole.ROLE_USER.toString());
    }
}
