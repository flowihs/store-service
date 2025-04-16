package nocast.storeservice.category.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.TestcontainersConfiguration;
import nocast.storeservice.category.dto.CategoryFilter;
import nocast.storeservice.category.dto.TreeViewOptions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@RequiredArgsConstructor
@Import(TestcontainersConfiguration.class)
@Rollback
@Sql(scripts = "classpath:test-category-data.sql", executionPhase = BEFORE_TEST_CLASS)
@ApplicationModuleTest
public class CategoryServiceTest {

    @Autowired
    private final CategoryService categoryService;

    @Test
    void simpleTest() {
        final var filter = CategoryFilter.builder().name("одежда").build();
        final var page = categoryService.findAll(
                new TreeViewOptions(0, 0), Pageable.unpaged(), filter
        );
        Assertions.assertThat(page)
                .isNotEmpty()
                .hasSize(2);
        page.forEach(System.out::println);
    }
}
