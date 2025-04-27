package nocast.storeservice.user.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.user.dto.UserCreateDto;
import nocast.storeservice.user.dto.UserFilter;
import nocast.storeservice.user.persistence.User;
import nocast.storeservice.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@RequiredArgsConstructor
@Import(TestcontainersConfiguration.class)
@Sql(scripts = "/test-user-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserServiceTest {
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll_Default() {
        final var actual = userService.findAll();
        assertThat(actual)
                .isNotNull()
                .hasSize(4)
                .allMatch(Objects::nonNull)
                .allMatch(it -> it.getId() != null)
                .anyMatch(it -> it.getUsername().equals("john_doe"))
                .anyMatch(it -> it.getId().equals(1L))
                .anyMatch(it -> it.getId().equals(4L));
    }

    @Test
    void findByIdTest() {
        Long id = 2L;
        final var actual = userService.findById(id);
        assertThat(actual)
                .isNotNull()
                .isPresent()
                .isNotEmpty();
        final var user = actual.get();
        assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("username", "jane_smith");
    }

    @Test
    void findAllByFilters() {
        Pageable page = PageRequest.of(0, 10);
        final var filter = UserFilter.builder().username("john").build();
        final var actual = userService.findAll(page, filter);
        assertThat(actual)
                .hasSize(1)
                .allMatch(Objects::nonNull)
                .isNotNull()
                .allMatch(it -> it.getId() != null
                        && it.getId().equals(1L))
                .allMatch(it -> it.getUsername() != null
                        && it.getUsername().equals("john_doe"))
                .allMatch(it -> it.getRoles() != null
                        && it.getRoles().getFirst().equals("ROLE_USER"));
    }

    @Test
    void createUserTest() {
        final var user = new UserCreateDto("username", "password", "email@gmail.com", "John", "+4803443432");
        userService.create(user);
        userRepository.findAll().forEach(System.out::println);
        final var result = userService.findById(5L);
        assertThat(result)
                .isNotNull()
                .isNotEmpty();
        final var resultUser = result.get();
        assertThat(resultUser)
                .isNotNull()
                .hasFieldOrPropertyWithValue("username", "username")
                .hasFieldOrPropertyWithValue("firstName", "John");
        assertThat(resultUser.getRoles())
                .allMatch(Objects::nonNull)
                .contains("ROLE_USER");
    }

//    @Test
//    void findAllByFilters_Roles() {
//        Pageable page = PageRequest.of(0, 10);
//        final var filter = UserFilter.builder().username("jane_smith").build();
//        final var actual = userService.findAll(page, filter);
//        assertThat(actual)
//                .hasSize(1)
//                .allMatch(Objects::nonNull)
//                .isNotNull()
//                .allMatch(it -> it.getId() != null
//                        && it.getId().equals(2L))
//                .allMatch(it -> it.getUsername() != null
//                        && it.getUsername().equals("jane_smith"))
//                .allMatch(it -> it.getRoles() != null
//                        && it.getRoles().getFirst().equals("ROLE_ADMIN"))
//                .allMatch(it -> it.getRoles().get(1).equals("ROLE_USER"));
//    }

}
